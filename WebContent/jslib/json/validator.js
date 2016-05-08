$.extend($.fn.validatebox.defaults.rules, {
	mobile : {// 验证手机号码
        validator : function(value) {
            return /^(13|15|18)\d{9}$/i.test(value);
        },
    	message : '手机号码格式不正确'
	},
	telephone:{//电话号码验证,格式010-12345678
		validator:function(value){
				return /^\d{3,4}?\d{7,8}$/.test(value);
			},
		message:"请正确填写您的电话号码."
	},
	intOrFloat  : {//输入数字和小数
		validator : function(value) {
            return /^\d+(\.\d+)?$/i.test(value);
    	},
    	message : '请输入数字，并确保格式正确'
	},
	integer : {// 验证整数 
        validator : function(value) { 
            return /^[+]?[1-9]+\d*$/i.test(value); 
        }, 
        message : '请输入整数' 
    }, 
    carNumber : {//车牌号验证
    	validator : function(value) {
            return /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/.test(value);
    	},
    	message : '车牌号格式不正确'
	},
	chinese : {// 中文
		validator : function(value) {
			 return /^[\Α-\￥]+$/i.test(value);
		},
		message : '请输入中文'
	},
	chOrEn : {// 中文或字母
		validator : function(value) {
			 return /^[\Α-\￥]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value);
		},
		message : '请输入中文或英文名称'
	},
      username : {// 验证用户名 
       validator : function(value) { 
           return /^[a-zA-Z][a-zA-Z0-9_]{2,15}$/i.test(value); 
       }, 
       message : '用户名不合法（字母开头，允许3-16字节，允许字母数字下划线）' 
   },
      same:{ 
       validator : function(value, param){       	
           if($("#"+param).val() !="" && value != ""){ 
               return $("#"+param).val() == value; 
           }else{ 
               return true; 
           } 
       }, 
       message : '两次输入的密码不一致！'   
   },  
   //判读是否低于最低保证金
   lowestMoney:{ 
       validator : function(value, param){ 
    	var temp=$(""+param+"").val();
    	if(parseInt(value)>=parseInt(temp))
    	{
    		return true;
    	}
    	return false;
       }, 
       message : '低于最低保证金！'   
   },
   	 idcard : {// 验证身份证 
       validator : function(value) { 
          return idCardFun(value); 
       }, 
       message : '身份证号码格式不正确' 
   },
   	cardNumber :{// 验证一卡通卡号 
        validator : function(value) { 
            return /^[A-Z0-9]{8,10}$/i.test(new String(value).toUpperCase()); 
        }, 
        message : '请输入10位有效卡号' 
   	},
   	seat :{// 验证席位(X+三位数字)
        validator : function(value) { 
            return /^X-(\d{3})$/i.test(value); 
              
        }, 
        message : '席位号由X_和三位数字组成(如X-001)' 
   	},
   	femaleOrMale :{// 验证性别（男/女）
        validator : function(value) { 
        	if("男" == value || "女" == value){
        		return true;
        	}else{
            	return false;
        	}
        }, 
        message : '请输入正确性别' 
   	},
 	varname :{//验证参数名
	    validator : function(value) { 
	        return /^[0-9A-Za-z_]$/i.test(value); 
	    }, 
	    message : '请输入英文或下划线' 
   	}
});

/**
 * 身份证验证
 * @param {} number
 * @return {Boolean}
 */
function idCardFun(number) {
    var area = { 
 		11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏", 33: "浙江",
        34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川",
        52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外"
    };
    var idcard, Y, JYM;
    var S, M;
    var idcard_array = new Array();
    idcard_array = number.split("");
    //地区检验
    if (area[parseInt(number.substr(0, 2))] == null) return false;
    //身份号码位数及格式检验
    switch (number.length) {
        case 15:
            if ((parseInt(number.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(number.substr(6, 2)) + 1900) % 100 == 0
		&& (parseInt(number.substr(6, 2)) + 1900) % 4 == 0)) {
                //测试出生日期的合法性
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;
            } else {
                //测试出生日期的合法性
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;
            }
            if (ereg.test(number)) {
                return true;
            } else {
                return false;
            }
            break;
        case 18:/*18位身份号码检测出生日期的合法性检查
            闰年月:
            ((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
            平年月日:
            ((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))*/
            if (parseInt(number.substr(6, 4)) % 4 == 0 || (parseInt(number.substr(6, 4)) % 100 == 0
		&& parseInt(number.substr(6, 4)) % 4 == 0)) {
                //闰年出生日期的合法性正则表达式
                ereg = /^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;
            } else {
                //平年出生日期的合法性正则表达式
                ereg = /^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;
            }
            //测试出生日期的合法性
            if (ereg.test(number)) {
                //计算校验位
                S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7 + (parseInt(idcard_array[1])
		  + parseInt(idcard_array[11])) * 9 + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
		  + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5 + (parseInt(idcard_array[4])
		  + parseInt(idcard_array[14])) * 8 + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
		  + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2 + parseInt(idcard_array[7]) * 1
		  + parseInt(idcard_array[8]) * 6 + parseInt(idcard_array[9]) * 3;
                Y = S % 11;
                M = "F";
                JYM = "10X98765432";
                M = JYM.substr(Y, 1); //判断校验位
                if (M == idcard_array[17]) return true; //检测ID的校验位
                else return false;
            } else return false;
            break;
        default:
            return false;
            break;
    }
}
