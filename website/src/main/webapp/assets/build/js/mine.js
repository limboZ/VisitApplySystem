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

//计算天数差的函数，通用
function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2002-12-18格式
    var  aDate,oDate1,oDate2,iDays;
    aDate  =  sDate1.split("-");
    oDate1  =  new  Date(aDate[1]  +  '/'  +  aDate[2]  +  '/'  +  aDate[0]);    //转换为12/18/2002格式,支持firefox
    aDate  =  sDate2.split("-");
    oDate2  =  new  Date(aDate[1]  +  '/'  +  aDate[2]  +  '/'  +  aDate[0]);
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24) ;   //把相差的毫秒数转换为天数
    return  iDays;
}
//通过oA号查询人员信息
function getUserByOa(oa) {
    if(!isEmpty(oa)){
        $.ajax({
            url:'${ctx}/oaPeopleInfo/' + oa,
            async:false,
            success:function (data) {
                if(data.tip == 0){
                    return data.data;
                }
            }
        })
    }
}
