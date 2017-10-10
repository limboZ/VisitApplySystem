/**
 * Created by limboZ on 2017/9/30.
 */
// 判断字符串是否为空
function isEmpty(str) {
    if(str.length == 0 || $.trim(str) == '' || str == '' || str.value == 'null'){
        return true;
    }else {
        return false;
    }
}

/** 实现tr的上移下移，位置互换 上移：.moveBefore 下移：.moveAfter  */
$('body').on('click','.moveBefore',function () {
    // var tr = $(this).parent().parent();
    var tr = $(this).parents('tr:first');
    var beforeTr = tr.prev();
    if(beforeTr.length > 0){  //即存在上一个兄弟节点，不是第一行
        tr.remove();
        beforeTr.before(tr);
    }
});
$('body').on('click','.moveAfter',function () {
    // var tr = $(this).parent().parent();
    var tr = $(this).parents('tr:first');
    var nextTr = tr.next();
    if(nextTr.length > 0){  //即存在下一个兄弟节点，不是最后一行
        tr.remove();
        nextTr.after(tr);
    }
});
