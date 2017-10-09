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