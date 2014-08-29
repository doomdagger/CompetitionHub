/**
 *
 * Created by JOECHOW on 2014/8/29.
 */

$(document).ready(function(){
    $(".nav-li").mouseover(function(){
        $(this).removeClass("btn-default");
        $(this).addClass("btn-info");
    });
    $(".nav-li").mouseout(function(){
        $(this).removeClass("btn-info");
        $(this).addClass("btn-default");
    });
});
