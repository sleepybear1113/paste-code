// axios 全局拦截器
axios.interceptors.response.use(
    // 如果返回的状态码为 200，说明接口请求成功，可以正常拿到数据
    (response) => {
        let res = response.data;
        let code = res.code;
        if (code === 0) {
            return response
        } else {
            let message = res.message;
            if (code > -10) {
                alert(message);
            } else {
                alert("系统错误！\n" + message);
            }
            // 直接拒绝往下面返回结果信息
            return Promise.reject(response);
        }
    },
    // 否则的话抛出错误
    (error) => {
        let response = error.response;
        if (response == null) {
            alert(`请求失败，请检查网络`);
            return Promise.reject(error);
        }
        let status = response.status;
        let request = error.request;
        alert(`请求失败，status = ${status}\n url：${request.responseURL}`);
        return Promise.reject(error);
    }
);