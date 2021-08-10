$(function(){

	//根据性别显示图片
	var sex = $(".sex").text().trim();
	if (sex === "女") {
		$("#sex-pic").attr("src","image/girl.png");
	}else if (sex === "男") {
		$("#sex-pic").attr("src","image/boy.png");
	}else{
		$("#sex-pic").attr("src","image/sex-unknow.png");
	};


	//切换选项卡，并根据对应选型卡显示按钮
	$(".card-btn").find("span").on("click",function(){
		var title = $(this).attr("title");
		var showName = title.split("-")[0];
		$(this).addClass("active").siblings("span").removeClass("active");
		$(this).parent().find("#"+showName+"-upload").show().siblings("a").hide();
		if(showName=="ask")
		{
            $(this).parent().find("#"+"delete-"+showName).show();
		}else{
			$(this).parent().find("#"+"delete-goods").show();
		}		
		$("#container").find("#"+title).show().siblings("div").hide();
	});

	//选择对应商品进行选择删除操作
	var goodsIdArr=[],goodsId;
	$("span.delete-btn").on("click",function(){

		goodsId = $(this).siblings("form").find(".goods-id").attr("value");
		//判断数组里是否有goodsId,无则返回-1
		if($.inArray(goodsId ,goodsIdArr) == - 1){ 
			$(this).css("background-image","url(image/delete1.jpg)");
			goodsIdArr.push(goodsId);
			// console.log(bookIdArr);
		}else{
			$(this).css("background-image","url(image/delete2.jpg)");
			goodsIdArr = $.grep(goodsIdArr,function(val){
				return val != goodsId;
			});
			// console.log(bookIdArr);			
		}		
	});


	// //点击删除图标，确认是否删除
	// var flag  = false;
	// $("#delete-goods").on("click",function(){
	// 	var flag = confirm("确定删除所选择的二手商品吗？");
	// })


});
