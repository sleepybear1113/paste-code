let homepage = window.location.origin + window.location.pathname;

// 公共 element 抽取
let codeLanguageSelect = document.getElementById("code-language-select");
let codeStyleSelect = document.getElementById("code-style-select");
let codeView = document.getElementById("code-view");
let codeInput = document.getElementById("code-input");
let pasteButton = document.getElementById("paste-button");
let pasteUrlText = document.getElementById("paste-url");
let pasteTimeText = document.getElementById("paste-time");

/**
 * 获取 url 后面的参数
 * @returns {{obj}}
 */
function getSearchObj() {
    let search = window.location.search.substring(1);
    if (search.length === 0) {
        return {};
    }
    let searches = search.split("&");
    let obj = {};
    for (let i = 0; i < searches.length; i++) {
        let item = searches[i];
        let split = item.split("=");
        if (split.length < 2) {
            continue;
        }
        obj[split[0]] = split[1];
    }

    return obj;
}

function optionElement(lang, languageName) {
    let optionElement = document.createElement("option");
    optionElement.value = lang;
    optionElement.text = languageName;
    return optionElement;
}

function displayTime(timestamp) {
    if (timestamp == null) {
        pasteTimeText.innerText = "";
        return;
    }

    pasteTimeText.innerText = new Date(parseInt(timestamp)).toLocaleString();
}