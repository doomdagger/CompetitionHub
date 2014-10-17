/**
 * Created by JOECHOW on 2014/10/13 0013.
 */

/**
 * 该页面所有信息将通过Ajax获得
 * *@param comptId:赛事ID，用于查找报名列表
 * @param filter:true为查看全部
 * @param order;true为顺序时间排列
 * @param currPage:当前页
 */
function flashNewPanel(currPage){

    //get filters
    var comptId = $("#comptLink").val();
    var filter = $("input[name='filter']:checked").val()==1?true:false;//1为查看已通过
    var order = $("#selOrderTime").val()==0?true:false;//0为顺序时间查看
    //var currPage = currPage?currPage:1;
    //刷新
    var url = "/man/compt/sign/getSignList";
    var data = {"comptId":comptId,"filter":filter,"order":order,"currPage":currPage};
    $.getJSON(url,data,function(result){
        result = $.parseJSON(result);
        /*init table view*/
        var tableView = "";
        var thView = "<tr>"+$("#thFilterView").html()+"</tr>"+"<tr class='bg-primary'>"+$("#thView").html()+"</tr>";
        tableView += thView;//先加上头部
        var signList = result.signList;
        for(var index in signList){
            tableView += "<tr>";
            tableView += "<td class=\"text-center\">"+(parseInt(index)+1)+"</td>";
            tableView += "<td class=\"text-center\"><a target=\"_blank\" href=\"/man/compt/sign/teamDetail/"+signList[index].teamNo+"\">"+signList[index].name+"</a></td>";
            tableView += "<td class=\"text-center\">"+signList[index].memberNum+"人</td>";
            tableView += "<td class=\"text-center\">"+signList[index].createtime.substr(0,signList[index].createtime.length-2)+"</td>";
            tableView += "<td class=\"text-center\">";
            tableView += "<input class=\"input-sm\" type=\"text\" maxlength=\"4\" width=\"50px\" value="+signList[index].orderNum+"></td>";
            tableView += "<td class=\"text-center\">";
            tableView += "<input type=\"checkbox\" class=\"chbItem\" /></td>";
            tableView += "<td class=\"text-center\">";
            tableView += "<a class=\"btn btn-xs btn-success\" alt="+signList[index].ID+">优先</a>&nbsp;";
            tableView += "<button class=\"btn btn-xs btn-warning\" alt="+signList[index].ID+">通过</button>";
            tableView += "</td>";
            tableView += "</tr>";
        }
        $("#tableSignList").html(tableView);

        /*init the pageable*/
        var pageView = "";
        var page = result.page;
        page.pageCount = parseInt(page.pageCount);
        page.currentPage = parseInt(page.currentPage);
        console.log("count:"+page.pageCount);
        console.log("current:"+page.currentPage);
        if(page.pageCount == 1){
            pageView += "<li><a href=\"javascript:void(0)\">&laquo;</a></li>";
            pageView += "<li class=\"active\"><a href=\"javascript:void(0)\">1</a></li>";
            pageView += "<li><a href=\"javascript:void(0)\">&raquo;</a></li>";
        }else{
            if(page.currentPage == 1){
                pageView += "<li><a href=\"javascript:void(0)\">&laquo;</a></li>";
            }else{
                pageView += "<li><a href=\"javascript:void(0)\" onclick=\"toTargetPage("+i+")\">&laquo;</a></li>";
            }
            for(var i= 0,size=page.pageCount;i<size;i++){
                if((i+1) == page.currentPage){
                    pageView += "<li class=\"active\"><a href=\"javascript:void(0)\" onclick=\"toTargetPage("+(i+1)+")\">"+(i+1)+"</a></li>";
                }else{
                    pageView += "<li><a href=\"javascript:void(0)\" onclick=\"toTargetPage("+(i+1)+")\">"+(i+1)+"</a></li>";
                }
            }
            if(page.currentPage == page.pageCount){
                pageView += "<li><a href=\"javascript:void(0)\">&raquo;</a></li>";
            }else{
                pageView += "<li><a href=\"javascript:void(0)\" onclick=\"toTargetPage("+(i+1+1)+")\">&raquo;</a></li>";
            }
        }
        $("#pageView").html(pageView);
    });
}

//跳转页面
function toTargetPage(target){
    flashNewPanel(target);
}


$(document).ready(function(){
    /**
     * 判断checkbox的方法
     * jquery判断checked的三种方法:
     .attr('checked):   //看版本1.6+返回:”checked”或”undefined” ;1.5-返回:true或false
     .prop('checked'): //16+:true/false
     .is(':checked'):    //所有版本:true/false//别忘记冒号哦
     所有的jquery版本都可以这样赋值:
     // $("#cb1").attr("checked","checked");
     // $("#cb1").attr("checked",true);
     //.prop("checked",true)
     */
    $("body").delegate("#chbForSelAll","click",function(){
        console.log("click event");
        if($(this).is(":checked")){
            console.log("set true");
            $(".chbItem").prop("checked",true);
            return;
        }
        console.log("set false");
        $(".chbItem").prop("checked",false);
    });

    $("body").delegate("#chbItem","focus",function(){

    });

    /*initSignList views*/
    (function(){
        flashNewPanel(1);
    })();
});