/**
 * Created by limboZ on 2017/10/9.
 * 监听表格append事件，根据日期排序
 */
function sort(obj) {
    obj.unbind();
    //得到所有的列
    var list = obj.find('tbody tr');
    // console.log(list);
    //转换列值，将日期和上下午转换为一个可比较的对象
    $(list).each(function () {
        var tr = $(this);
        //转换，上午设为9：00，下午设为14：00,再根据日期进行比较
        var date = tr.find('.tDate').text();
        var time = tr.find('.tTime').text();
        if(time == '上午'){
            time = ' 09:00:00';
        }else {
            time = ' 14:00:00';
        }
        //转换为yyyy/mm/dd HH:mm:ss 格式，解决ie9不兼容问题
        // var dateTime = new Date((date + time).replace('-','/').replace('-','/'));
        var dateTime = (date + time).replace('-','/').replace('-','/');
        tr.addClass(dateTime);
    });
    //根据时间排序
    var len = list.length;
    var minIndex,temp;
    for(var i = 0;i < len -1;i++){
        minIndex = i;
        for(var j = i + 1;j<len;j++){
            if(new Date($(list.get(j)).attr('class')) < new Date($(list.get(minIndex)).attr('class'))){
                minIndex = j;
            }
        }
        temp = list[i];
        list[i] = list[minIndex];
        list[minIndex] = temp;
        // temp = list.get(i);
        // list.remove(i);
        // list.add(i - 1,list.get(minIndex - 1));
        // list.remove(minIndex);
        // list.add(minIndex -1,temp);
    }
    var f = list;
    //重新append
    obj.find('tbody').empty();
    // $(f).each(function () {
    //     var tr = $(this);
    //     obj.find('tbody').append(tr);
    // });
    for(var i = 0;i < f.length;i++){
        obj.append(f[i]);
    }
    // var newObj = $('.sortSummary');
    $('.sortSummary').bind('DOMNodeInserted',function () {
        sort($(this));
    });
}
$().ready(function () {

    $('.sortSummary').bind('DOMNodeInserted',function () {
        sort($(this));
    });
    // $('.sortSummary').bind('DOMNodeInserted',function () {
    //     var obj = $(this);
    //     //移除该class，在所有元素重新append之后再重新加上该class
    //     // $(this).removeClass('sortSummary');
    //     // obj.unbind('DOMNodeInserted');
    //     obj.unbind();
    //     //得到所有的列
    //     var list = obj.find('tbody tr');
    //     // console.log(list);
    //     //转换列值，将日期和上下午转换为一个可比较的对象
    //     $(list).each(function () {
    //         var tr = $(this);
    //         //转换，上午设为9：00，下午设为14：00,再根据日期进行比较
    //         var date = tr.find('.tDate').text();
    //         var time = tr.find('.tTime').text();
    //         if(time == '上午'){
    //             time = ' 09:00:00';
    //         }else {
    //             time = ' 14:00:00';
    //         }
    //         //转换为yyyy/mm/dd HH:mm:ss 格式，解决ie9不兼容问题
    //         // var dateTime = new Date((date + time).replace('-','/').replace('-','/'));
    //         var dateTime = (date + time).replace('-','/').replace('-','/');
    //         tr.addClass(dateTime);
    //     });
    //     //根据时间排序
    //     var len = list.length;
    //     var minIndex,temp;
    //     for(var i = 0;i < len -1;i++){
    //         minIndex = i;
    //         for(var j = i + 1;j<len;j++){
    //             if(new Date($(list.get(j)).attr('class')) < new Date($(list.get(minIndex)).attr('class'))){
    //                 minIndex = j;
    //             }
    //         }
    //         temp = list[i];
    //         list[i] = list[minIndex];
    //         list[minIndex] = temp;
    //         // temp = list.get(i);
    //         // list.remove(i);
    //         // list.add(i - 1,list.get(minIndex - 1));
    //         // list.remove(minIndex);
    //         // list.add(minIndex -1,temp);
    //     }
    //     var f = list;
    //     //重新append
    //     obj.find('tbody').empty();
    //     // $(f).each(function () {
    //     //     var tr = $(this);
    //     //     obj.find('tbody').append(tr);
    //     // });
    //     for(var i = 0;i < f.length;i++){
    //         obj.append(f[i]);
    //     }
    //     //重新加上该class
    //     // $(this).addClass('sortSummary');
    //     $(this).bind('DOMNodeInserted');
    // });
})
