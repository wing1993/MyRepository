/**
 * 公共方法
 * */
$.extend({
	/**
	 * @param url:请求地址   postData:传递的参数  sCallback:请求成功回调  eCallback:请求失败回调
	 * */
	ajaxPost: function(url, postData, sCallback, eCallback) {
		var postData = postData ? postData : {};
		$.ajax({
			type: "POST",
			url: url,
			data: postData,
			dataType: "json",
			success: function(data) {
				sCallback && sCallback(data);
			},
			error: function(err) {
				$.fn.BHTips({
					type: 'warn',
					content: '错误'
				});
				eCallback && eCallback(data);
			}
		});
	},

	ajaxGet: function(url, getData, sCallback, eCallback) {
		var getData = getData ? getData : {};
		$.ajax({
			type: "get",
			url: url,
			data: getData,
			dataType: "json",
			success: function(data) {
				sCallback && sCallback(data);
			},
			error: function(err) {
				eCallback && eCallback(data);
			}
		});
	}
});