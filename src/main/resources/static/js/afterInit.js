// 设置 title 的 href
document.getElementById("title").setAttribute("href", homepage);

// 初始化代码风格的下拉框
let codeStyleSelect = document.getElementById("code-style-select");
document.querySelectorAll(".code-css").forEach((e) => {
    codeStyleSelect.add(optionElement(e.getAttribute("title")));
});

// 初始化格式化语言的下拉框
let codeLanguageSelect = document.getElementById("code-language-select");
languages.forEach((language) => {
    codeLanguageSelect.add(optionElement(language));
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

    let url = "get";
    axios.get(url, {params: searchObj}).then((res) => {
        let pasteCode = new PasteCode(res.data.result);
        if (pasteCode == null || pasteCode.code == null || pasteCode.code.length === 0) {
            return;
        }
        document.getElementById("paste").style.display = "none";
        document.getElementById("code-input").value = pasteCode.code;
        document.getElementById("code-language-select").value = pasteCode.language;
        document.getElementById("code-style-select").value = pasteCode.style;
        document.getElementById("code-input").readOnly = true;
        selectCodeStyle(pasteCode.style);
        formatCode();
        setPasteUrl(pasteCode.eid);
    });
}

initPasteCode();