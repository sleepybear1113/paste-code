/**
 * 选择代码风格
 */
function selectCodeStyle(value) {
    document.querySelectorAll(".code-css").forEach((e) => {
        let attribute = e.getAttribute("title");
        e.disabled = attribute !== value;
    });
}

/**
 * 选择代码语言
 */
function changeLanguage() {
    codeView.removeAttribute("class");
    let language = codeLanguageSelect.value;
    if (language === "") {
        return;
    }
    codeView.classList.add("language-" + language.toLowerCase());
    codeView.classList.add("hljs");
}

function clearLanguage() {
    codeLanguageSelect.selectedIndex = 0;
}

function autoFormat() {
    clearLanguage();
    formatCode();
}

/**
 * 格式化代码
 */
function formatCode() {
    changeLanguage();
    document.querySelectorAll('pre code').forEach((el) => {
        el.innerHTML = htmlDecode(codeInput.value);
        hljs.highlightElement(el);
        hljs.lineNumbersBlock(el);
        disablePasteButton(false);
        callbackCodeLanguage();
        if (pasteButton.style.display !== "none" && !pasteButton.disabled) {
            displayTime();
        }
    });
}

/**
 * 格式化代码之后，从 code 处获取代码语言回填到下拉框中
 */
function callbackCodeLanguage() {
    let classList = codeView.classList;
    let currentLanguage = null;
    for (let i = 0; i < classList.length; i++) {
        let item = classList[i];
        let searchString = "language-";
        if (item.startsWith(searchString) && item.length > searchString.length) {
            currentLanguage = item.replace(searchString, "");
        }
    }
    if (currentLanguage == null) {
        return;
    }
    if (currentLanguage === "undefined") {
        codeLanguageSelect.selectedIndex = 1;
    } else {
        for (let i = 0; i < codeLanguageSelect.options.length; i++) {
            let option = codeLanguageSelect.options[i];
            if (option.value === currentLanguage) {
                option.selected = true;
                break;
            }
        }
    }
}

/**
 * 对于 HTML 语言进行反转义
 * @param str HTML
 * @returns {string|*|string} 反转义
 */
function htmlDecode(str) {
    let s = "";
    if (str.length === 0) {
        return s;
    }
    s = str.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/'/g, "&#39;").replace(/"/g, "&quot;");
    return s;
}

/**
 * 将内容保存到服务器
 */
function pasteCode() {
    let code = codeInput.value;
    if (code.length === 0) {
        alert("内容为空");
        return;
    }

    formatCode();

    disablePasteButton(true);

    let language = codeLanguageSelect.value;
    let style = codeStyleSelect.value;
    let adminKey = adminKeyInput.value;

    let data = {code, language, style, adminKey};

    let url = "/paste";
    axios.post(url, data).then(res => {
        let pasteCode = new PasteCode(res.data.result);
        setPasteUrl(pasteCode.eid);
        displayTime(new Date().getTime());
    });
}

function disablePasteButton(b) {
    pasteButton.disabled = (b === true);
}

function setPasteUrl(eid) {
    pasteUrlText.innerText = homepage + "?eid=" + eid;
}