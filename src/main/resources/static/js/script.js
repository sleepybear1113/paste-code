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
    let language = document.getElementById("code-language-select").value;
    let code = document.getElementById("code");
    code.removeAttribute("class");
    code.classList.add("language-" + language.toLowerCase());
    code.classList.add("hljs");
}

/**
 * 格式化代码
 */
function formatCode() {
    changeLanguage();
    document.querySelectorAll('pre code').forEach((el) => {
        el.innerHTML = htmlDecode(document.getElementById("code-input").value);
        hljs.highlightElement(el);
        hljs.lineNumbersBlock(el);
        disablePasteButton(false);
    });
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
function paste() {
    let code = document.getElementById("code-input").value;
    if (code.length === 0) {
        alert("内容为空");
        return;
    }

    formatCode();

    disablePasteButton(true);

    let language = document.getElementById("code-language-select").value;
    let style = document.getElementById("code-style-select").value;

    let data = {code, language, style};

    let url = "paste";
    axios.post(url, data).then(res => {
        let pasteCode = new PasteCode(res.data.result);
        setPasteUrl(pasteCode.eid);
    });
}

function disablePasteButton(b) {
    document.getElementById("paste").disabled = (b === true);
}

function setPasteUrl(eid) {
    document.getElementById("paste-url").innerText = homepage + "?eid=" + eid;
}