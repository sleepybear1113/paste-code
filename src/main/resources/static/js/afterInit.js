// 设置 title 的 href
document.getElementById("title").setAttribute("href", homepage);

// 初始化代码风格的下拉框
document.querySelectorAll(".code-css").forEach((e) => {
    let attribute = e.getAttribute("title");
    codeStyleSelect.add(optionElement(attribute, attribute));
});

// 初始化格式化语言的下拉框
let languages = hljs.listLanguages();
let plaintext = "plaintext";
codeLanguageSelect.add(optionElement("", "自动检测"));
codeLanguageSelect.add(optionElement(plaintext, "纯文本"));
languages.forEach((language) => {
    if (language !== plaintext) {
        codeLanguageSelect.add(optionElement(language, hljs.getLanguage(language).name));
    }
});

/**
 * 如果 url 后面带有 eid=xxx，那么走后端查询回填
 */
function initPasteCode() {
    let searchObj = getSearchObj();
    let eid = searchObj.eid;
    if (eid == null || eid.length === 0) {
        return;
    }

    let url = "/get";
    axios.get(url, {params: searchObj}).then((res) => {
        let pasteCode = new PasteCode(res.data.result);
        if (pasteCode == null || pasteCode.code == null || pasteCode.code.length === 0) {
            return;
        }
        pasteButton.style.display = "none";
        codeInput.value = pasteCode.code;
        codeLanguageSelect.value = pasteCode.language;
        codeStyleSelect.value = pasteCode.style;
        displayTime(pasteCode.modifyTime);
        codeInput.readOnly = true;
        selectCodeStyle(pasteCode.style);
        formatCode();
        setPasteUrl(pasteCode.eid);
    });
}

initPasteCode();