let homepage = window.location.origin + window.location.pathname;

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

function optionElement(attr) {
    let optionElement = document.createElement("option");
    optionElement.text = attr;
    optionElement.value = attr;
    return optionElement;
}