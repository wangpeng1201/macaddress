// 定义全局变量


//切换iframe框架的src
function load(url) {/*const context = getContext();*/
    console.log("url:" + context + url);
    // 如果在当前页面找不到iframe，则在父窗口去找iframe
    if (window.self !== window.top) {
        $('#ifm', parent.document).attr('src', context + url);
    } else {
        $('#ifm').attr('src', context + url);
    }
}

/*const context = getContext();*/

/**
 * 获取上下文路径
 * */
function getContext() {
    let pathName = document.location.pathname;
    var i = pathName.indexOf('/heimdalls');
    if (i < 0)
        return "/";
    else {
        let index = pathName.substr(1).indexOf('/');
        return pathName.substr(0, index + 1) + '/';
    }
}

/**
 * 获取跳转路径
 * */
function toUrl(url, params) {
    // 定义全局变量
    url = context + url;
    let paramsArr = [];
    if (params) {
        Object.keys(params).forEach(item => {
            paramsArr.push(item + '=' + params[item]);
        });
        if (url.search(/\?/) === -1) {
            url += '?' + paramsArr.join('&');
        } else {
            url += '&' + paramsArr.join('&');
        }
    }
    console.log(url);
    return url;
}

/**
 * 截取地址栏参数
 * */
UrlParm = function () { // url参数
    let data, index;
    (function init() {
        data = [];
        index = {};
        let u = window.location.search.substr(1);
        if (u !== '') {
            let parms = decodeURIComponent(u).replace(/&amp;/g, "&").split('&');
            for (let i = 0, len = parms.length; i < len; i++) {
                if (parms[i] !== '') {
                    let p = parms[i].split("=");
                    if (p.length === 1 || (p.length === 2 && p[1] === '')) { // p | p=
                        data.push(['']);
                        index[p[0]] = data.length - 1;
                    } else if (typeof (p[0]) == 'undefined' || p[0] === '') { // =c | =
                        data[0] = [p[1]];
                    } else if (typeof (index[p[0]]) == 'undefined') { // c=aaa
                        data.push([p[1]]);
                        index[p[0]] = data.length - 1;
                    } else { // c=aaa
                        data[index[p[0]]].push(p[1]);
                    }
                }
            }
        }
    })();
    return {
        // 获得参数,类似request.getParameter()
        parm: function (o) { // o: 参数名或者参数次序
            try {
                return (typeof (o) == 'number' ? data[o][0] : data[index[o]][0]);
            } catch (e) {
            }
        },
        //获得参数组, 类似request.getParameterValues()
        parmValues: function (o) { // o: 参数名或者参数次序
            try {
                return (typeof (o) == 'number' ? data[o] : data[index[o]]);
            } catch (e) {
            }
        },
        //是否含有parmName参数
        hasParm: function (parmName) {
            return typeof (parmName) == 'string' ? typeof (index[parmName]) != 'undefined' : false;
        },
        // 获得参数Map ,类似request.getParameterMap()
        parmMap: function () {
            var map = {};
            try {
                for (var p in index) {
                    map[p] = data[index[p]];
                }
            } catch (e) {
            }
            return map;
        }
    }
}();

// jq 序列化表單
function formData(selector) {
    let data = {}, original = $(selector).serializeArray();
    for (let item of original) {
        data[item.name] = item.value;
    }
    console.log(data);
    return data;
}

// 關閉彈出層
function closeFrame(index) {
    let id = index || parent.layer.index;
    if (index === undefined) {
        parent.layer.close(id);
    } else {
        layer.close(id);
    }

}

//定制版通知， 3s自動消失
function pop(message, endFunction) {
    let end = endFunction || closeFrame;
    layer.msg(message, {icon: 1, end: end});
}

function error(message, endFunction) {
    let end = endFunction || closeFrame;
    layer.msg(message, {icon: 2, end: end});
}

function warn(message, endFunction) {
    let end = endFunction || closeFrame;
    layer.msg(message, {icon: 0, end: end});
}

//修改layui的分页，改成英文版
function page_en() {
    let oldhtml = $(".layui-table-page").html();
    $(".layui-table-page").html(
        oldhtml.replace(/页/g, 'Page')
            .replace(/到第/g, 'To')
            .replace(/确定/g, 'GO')
            .replace(/条/g, 'items')
            .replace(/共/g, 'Total'));
}


//定制版confirm
function affirm(message, confirmFunction, cancelFunction) {

    let confirm = confirmFunction || closeFrame, cancel = cancelFunction || closeFrame;

    layer.confirm(message,
        {
            icon: 3,
            title: 'prompt',
            skin: 'layui-layer-molv',
            resize: false,
            btn: ['Yes', 'No'],
            btn1: confirm,
            btn2: cancel,
        }
    );
}

//序列化表单
function serializeForm(form) {
    var obj = {};
    $.each(form.serializeArray(), function (index) {
        if (obj[this['name']]) {
            obj[this['name']] = obj[this['name']] + ',' + this['value'];
        } else {
            obj[this['name']] = this['value'];
        }
    });
    return obj;
}

// frame彈層統一方法
function frame(title, area, url, end) {
    layer.open({
        type: 2,
        title: title,
        shadeClose: true,
        maxmin: true,
        shade: 0.8,
        area: area,
        end: end,
        content: url,
        skin: 'layui-layer-molv',
    });
}


function show_shopm(t) {
    let row = $(t).attr('data-d'); //获取显示内容
    //小tips
    layer.tips(row, t, {
        tips: [1, '#3595CC'],
        time: 4000
    })
}

function addPopPage(titleName, id, forwardUrl, saveUrl, table) {
    layer.open({
        type: 2
        , title: '<p class="title-text">' + titleName + '</p>'
        , content: ctxPath + forwardUrl + '/' + id
        , area: ['600px', '450px']
        , btn: ['Submit', 'Cancel']
        , yes: function (index, layero) {
            let iframeWindow = window['layui-layer-iframe' + index]
                , submitID = 'LAY-addFunction-submit'  //此处跟弹出层中隐藏的submit按钮id相同
                , submit = layero.find('iframe').contents().find('#' + submitID);
            //监听提交
            iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                let field = data.field; //获取提交的字段
                console.log(field);
                //提交 Ajax 成功后，静态更新表格中的数据
                $.ajax({
                    type: "post",
                    url: ctxPath + saveUrl,
                    data: data.field,
                    dataType: "json",
                    success: function (data) {
                        console.log("data:", data);
                        layer.msg(data.msg);
                        table.reload();
                        layer.close(index); //关闭弹层
                    }
                });

            });
            submit.trigger('click');
        }
    });

}

/**
 * 关闭当前弹出层
 */
function closeWindow() {
    let index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

/**
 * 删除操作弹框
 * */
function deletePop(url,table) {
    layer.confirm(delTip,
        {
            title: [ information, 'font-size:15px;font-weight: bold'],
            // area:['240px','160px'], //弹框的大小
            btn: [ confirmBtn,cancelBtn ]
        },
        function () {
            $.ajax({
                type: "post",
                //url: ctxPath + 'product/del/' + data.id,
                url: url,
                dataType: "json",
                success: function (data) {
                    console.log("data:", data);
                    if (data.isex === false) {
                        if (data.msg === 'success') {
                            layer.msg(operationSuccess, {icon: 1, time: 2000}, function () {
                                table.reload();
                            });
                        } else {
                            layer.msg(operationWarning, {icon: 0, time: 3000});
                        }
                    } else {
                        layer.msg(operationFault, {icon: 2, time: 3000});
                    }
                }
            });
        });

}




