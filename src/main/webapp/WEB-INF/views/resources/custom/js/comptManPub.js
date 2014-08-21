/**
 * Created by JOECHOW on 2014/8/21.
 */
/**
 * Created by joechow on 14-5-28.
 */

$(document).ready(function(){

    /*小组赛 和 个人赛的切换*/
    $('#comType1').click(function(){
        $('#comType2Text').fadeOut();
    });
    $('#comType2').click(function(){
        $('#comType2Text').fadeIn();
    });

    /*上传附件说明*/
    $('#comIsNeedWorks1').click(function(){
        $('#comIsNeedWorks').fadeIn();
    });
    $('#comIsNeedWorks2').click(function(){
        $('#comIsNeedWorks').fadeOut();
    });

    /*上传附件说明*/
    $('#comIsSupport1').click(function(){
        $('#comIsSupport').fadeIn();
    });
    $('#comIsSupport2').click(function(){
        $('#comIsSupport').fadeOut();
    });

});