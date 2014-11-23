/**
 * Created by JOECHOW on 2014/10/13 0013.
 */

var thView = "<tr id='thFilterView'>"+$("#thFilterView").html()+"</tr>"+"<tr class='bg-primary' id='thView'>"+$("#thView").html()+"</tr>";
var flashTime = 5;
var waittingTime = 30;
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
        tableView += thView;//先加上头部
        var signList = result.signList;
        if(signList.length > 0){
        	for(var index in signList){
                tableView += "<tr>";
                tableView += "<td class=\"text-center\">"+(parseInt(index)+1)+"</td>";
                tableView += "<td class=\"text-center\"><a target=\"_blank\" href=\"/man/compt/sign/teamDetail/"+signList[index].teamNo+"\">"+signList[index].name+"</a></td>";
                tableView += "<td class=\"text-center\">"+signList[index].memberNum+"人</td>";
                tableView += "<td class=\"text-center\">"+signList[index].teacher+"</td>";
                tableView += "<td class=\"text-center\">"+signList[index].createtime.substr(0,signList[index].createtime.length-2)+"</td>";
                tableView += "<td class=\"text-center\">";
                tableView += "<input class=\"input-sm inputOrderItem\" type=\"text\" maxlength=\"4\" width=\"50px\" value="+signList[index].orderNum+" alt="+signList[index].ID+"></td>";
                tableView += "<td class=\"text-center\">";
                tableView += "<input type=\"checkbox\" class=\"chbItem\" name='chbItem' alt="+signList[index].ID+" /></td>";
                tableView += "<td class=\"text-center\">";
                //设置优先 和 取消优先的button
                //console.log(typeof signList[index].isPriority);
                if(signList[index].isPriority){
                	tableView += "<a class=\"btn btn-xs btn-success btnForCancelPriority\" alt="+signList[index].ID+">取消优先</a>&nbsp;";
                	tableView += "<a class=\"btn btn-xs btn-success btnForPriority\" alt="+signList[index].ID+" style='display:none'>优先</a>&nbsp;";
                }else{
                	tableView += "<a class=\"btn btn-xs btn-success btnForCancelPriority\" alt="+signList[index].ID+" style='display:none'>取消优先</a>&nbsp;";
                	tableView += "<a class=\"btn btn-xs btn-success btnForPriority\" alt="+signList[index].ID+">优先</a>&nbsp;";
                }
                //设施通过 和 取消通过的button
                if(signList[index].isPass){
                	tableView += "<button class=\"btn btn-xs btn-warning btnForCancelPass\" alt="+signList[index].ID+">取消通过</button>";
                	tableView += "<button class=\"btn btn-xs btn-warning btnForPass\" alt="+signList[index].ID+" style='display:none'>通过</button>";
                }else{
                	tableView += "<button class=\"btn btn-xs btn-warning btnForCancelPass\" alt="+signList[index].ID+"  style='display:none'>取消通过</button>";
                	tableView += "<button class=\"btn btn-xs btn-warning btnForPass\" alt="+signList[index].ID+">通过</button>";
                }
                
                tableView += "</td>";
                tableView += "</tr>";
            }
        }else{
        	tableView += "<tr>";
        	tableView += "<td colspan='8' class='text-center'><b>目前还没有参赛者报名</b></td>";
        	tableView += "</tr>";
        }
        
        $("#tableSignList").html(tableView);

        /*init the pageable*/
        var pageView = "";
        var page = result.page;
        page.pageCount = parseInt(page.pageCount);
        page.currentPage = parseInt(page.currentPage);
        $("#currPage").val(page.currentPage);//set the currPage hidden value
        //console.log("count:"+page.pageCount);
        //console.log("current:"+page.currentPage);
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
        //恢复filter和order的状态
        if(filter){
        	$("input[name='filter']:eq(1)").attr("checked",true);
        }
        if(!order){
        	$("#selOrderTime option[value='1']").attr("selected", true);
        }
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
            $(".chbItem").prop("checked",true);
            return;
        }
        $(".chbItem").prop("checked",false);
    });
    
    /*
     * flash the table
     */
    $("body").on("click","#btnForFlashSignListTable",function(){
    	/*setTimeout(function(){
    		flashTime--;
    	},1500);
    	while(!flashTime){
    		alert("刷新次数过多，30秒后才能继续刷新");
    		$(this).attr("disabled",true);
    		var originContent = $(this).text();
    		var timer = setInterval(function(){
    			while(waittingTime){
    				$(this).text(originContent+"("+(--waittingTime)+")");
    			}
    		},1000);
    		$(this).text(originContent);
    		clearInterval(timer);
    		flashTime = 5;
    		waittingTime = 30;
    	}*/
    	var currPage = $("#currPage").val();
    	flashNewPanel(currPage);
    });
    /*
     * set the orderNum
     */
    $("body").on("change",".inputOrderItem",function(){
    	//console.log("change event catch");
    	//var btnView = "<button class='btn btn-xs btn-warning btnForSaveOrderNum' alt="+$(this).attr("alt")+">保存</button>";
    	//$(this).after(btnView);
    	var signLink = $(this).attr("alt");
    	var orderNum = $(this).val();
    	//ajax to save this
    	var url = "/man/compt/sign/updateSignOrderNum";
    	var data = {"signLink":signLink,"orderNum":orderNum};
    	//var currentSaveBtn = $(this);
    	$.post(url,data,function(result){
    		if(result == "AJAX_SUCCESS"){
    			//$(this).fadeOut();
    		}else{
    			alert("操作失败，请重试");
    		}
    	});
    });
    
    /*
     * set the orderNum
     */
    /*$("body").on("click",".btnForSaveOrderNum",function(){
    	var signLink = $(this).attr("alt");
    	var orderNum = $(this).prev().val();
    	//ajax to save this
    	var url = "/man/compt/sign/updateSignOrderNum";
    	var data = {"signLink":signLink,"orderNum":orderNum};
    	var currentSaveBtn = $(this);
    	$.post(url,data,function(result){
    		console.log("result:"+result);
    		if(result == "AJAX_SUCCESS"){
    			$(this).fadeOut();
    		}else{
    			alert("操作失败，请重试");
    		}
    	});
    });*/
    
    /*
     * set the priority
     */
    $("body").on("click",".btnForPriority",function(){
    	var signLink = $(this).attr("alt");
    	var url = "/man/compt/sign/updateSignPriority";
    	var data = {"signLink":signLink,"isPriority":true};//isPriority:true为设置为优先，false取消优先
    	var currentBtn = $(this);
    	$.post(url,data,function(result){
    		if(result == "AJAX_SUCCESS"){
    			currentBtn.hide();
    			currentBtn.parent().find(".btnForCancelPriority").fadeIn();
    			currentBtn.parent().parent().addClass("bg-success");
    		}else{
    			alert("操作失败，请重试");
    		}
    	});
    });
    /*
     * cancel the priority
     */
    $("body").on("click",".btnForCancelPriority",function(){
    	var signLink = $(this).attr("alt");
    	var url = "/man/compt/sign/updateSignPriority";
    	var data = {"signLink":signLink,"isPriority":false};//isPriority:true为设置为优先，false取消优先
    	var currentBtn = $(this);
    	$.post(url,data,function(result){
    		if(result == "AJAX_SUCCESS"){
    			currentBtn.hide();
    			currentBtn.parent().find(".btnForPriority").fadeIn();
    			currentBtn.parent().parent().removeClass("bg-success");
    		}else{
    			alert("操作失败，请重试");
    		}
    	});
    });
    
    /*
     * set the sign pass
     */
    $("body").on("click",".btnForPass",function(){
    	var signLink = $(this).attr("alt");
    	var url = "/man/compt/sign/updateSignPass";
    	var data = {"signLink":signLink,"isPass":true};
    	var currentBtn = $(this);
    	$.post(url,data,function(result){
    		if(result == "AJAX_SUCCESS"){
    			currentBtn.hide();
    			currentBtn.parent().find(".btnForCancelPass").fadeIn();
    			currentBtn.parent().parent().addClass("bg-warning");
    		}else{
    			alert("操作失败，请重试");
    		}
    	});
    });
    /*
     * set the sign cancel pass
     */
    $("body").on("click",".btnForCancelPass",function(){
    	var signLink = $(this).attr("alt");
    	var url = "/man/compt/sign/updateSignPass";
    	var data = {"signLink":signLink,"isPass":false};
    	var currentBtn = $(this);
    	$.post(url,data,function(result){
    		if(result == "AJAX_SUCCESS"){
    			currentBtn.hide();
    			currentBtn.parent().find(".btnForPass").fadeIn();
    			currentBtn.parent().parent().removeClass("bg-warning");
    		}else{
    			alert("操作失败，请重试");
    		}
    	});
    });
    
    /*
     * set the selected priority
     */
    $("body").on("click","#btnForPrioritySelected",function(){
    	var signList = "";
    	$("input:checkbox[name='chbItem']:checked").each(function(i){
    		signList += $(this).attr("alt")+",";
    	});
    	signList = signList.substring(0, signList.length-1);
    	//ajax set the priority
    	var url = "/man/compt/sign/updateMultiSignPriority";
    	var data = {"signList":signList};
    	$.post(url,data,function(result){
    		if(result == "AJAX_SUCCESS"){
    			var currPage = $("#currPage").val();
    			flashNewPanel(currPage);
    		}else{
    			alert("操作失败，请重试");
    		}
    	});
    });
    /*
     * set the selected pass
     */
    $("body").on("click","#btnForPassSelected",function(){
    	var signList = "";
    	$("input:checkbox[name='chbItem']:checked").each(function(i){
    		signList += $(this).attr("alt")+",";
    	});
    	signList = signList.substring(0, signList.length-1);
    	//ajax set the pass
    	var url = "/man/compt/sign/updateMultiSignPass";
    	var data = {"signList":signList};
    	$.post(url,data,function(result){
    		if(result == "AJAX_SUCCESS"){
    			var currPage = $("#currPage").val();
    			flashNewPanel(currPage);
    		}else{
    			alert("操作失败，请重试");
    		}
    	});
    });
    
    /*
     * 监听filter和order
     */
    $("body").on("click","input[name='filter']",function(){
    	flashNewPanel($("#currPage").val());
    });
    $("body").on("change","#selOrderTime",function(){
    	flashNewPanel($("#currPage").val());
    });
    
    /*initSignList views*/
    (function(){
        flashNewPanel(1);
    })();
});