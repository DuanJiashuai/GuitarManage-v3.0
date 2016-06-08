/**
 * 
 */

$(document).ready(function(){
	$.getJSON("AllGuitars",function(json){
		var html="";
		if(json.length==0){
			$(".table").append("<tr><td colspan=\"8\" class=\"warning\" style='text-align:center;'>当前暂无商品信息</td></tr>");
		}else{
			for(var i=0;i<json.length;i++){
				var serialNumber=json[i].serialNumber;
				var price=json[i].price;
				var builder=json[i].builder;
				var model=json[i].model;
				var type=json[i].type;
				var backwood=json[i].backWood;
				var topwood=json[i].topWood;
				var stringNum=json[i].StringNum;
				html+="<tr><td><input type='checkbox' class='check' name='guitarId' id='guitarId' value='"+serialNumber+"' /></td><td>"+serialNumber+"</td><td>"+builder+"</td><td>"+model+"</td><td>"+type+"</td>";
				html+="<td>"+stringNum+"</td><td>"+topwood+"</td><td>"+backwood+"</td><td>"+price+"</td></tr>";
			}
			$(".table").append(html);
		}
		$('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
			increaseArea: '20%'
		});
	});
});

function checkAll(){
	var p=false;
	$(".icheckbox_square-blue").each(function(){
		//只要有一个未选中就为true，可执行全选操作
		if($(this).css("background-position")!="-48px 0px"){
			p=true;
			return false;//跳出循环
		};
	});	
	if(p){
		$(".icheckbox_square-blue").each(function(){
			$(this).attr("class","icheckbox_square-blue checked");
			$(".check").attr("checked","checked");
		});
	}else{
		$(".icheckbox_square-blue").each(function(){
			$(this).attr("class","icheckbox_square-blue");
			$(".check").removeAttr("checked");
		});
	}
}

function deleteGuitar(){
	var count=0;
	var guitarId=new Array();
	$(".icheckbox_square-blue").each(function(){
		if($(this).css("background-position")=="-48px 0px"){
			count++;
			guitarId[count]=$(this).children("input").val();
		};
	});
	if(count==0){
		alert("请至少选择一把吉他");
	}else{
		if(confirm("操作不可恢复，确认要删除选中的试吉他？")==true){
			window.location.href="DeleteGuitars?count="+count+"&guitarId="+guitarId; 
		}
	}
}