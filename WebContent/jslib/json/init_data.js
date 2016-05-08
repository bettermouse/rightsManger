var carTypeJson = [
	{"code" : "2","text" : "半挂车"}
	,{"code" : "3","text" : "平板车"}
	,{"code" : "4","text" : "高栏车"}
	,{"code" : "5","text" : "集装车"}
	,{"code" : "6","text" : "冷藏车"}
	,{"code" : "7","text" : "单桥车"}
	,{"code" : "8","text" : "双桥车"}
	,{"code" : "9","text" : "半封闭"}
	,{"code" : "10","text" : "特种车"}
	,{"code" : "11","text" : "商品运输车"}
	,{"code" : "12","text" : "危险品车"}
	,{"code" : "13","text" : "挂车"}
	,{"code" : "14","text" : "爬梯车"}
	,{"code" : "15","text" : "可拼车"}
	,{"code" : "16","text" : "低栏车"}
	,{"code" : "20","text" : "半挂一拖二"}
	,{"code" : "21","text" : "半挂一拖三"}
	,{"code" : "22","text" : "半挂二拖二"}
	,{"code" : "23","text" : "半挂二拖三"}
	,{"code" : "24","text" : "前四后四"}
	,{"code" : "25","text" : "前四后六"}
	,{"code" : "26","text" : "前四后八"}
	,{"code" : "27","text" : "前四后十"}
	,{"code" : "28","text" : "五轮车"}
	,{"code" : "29","text" : "后八轮"}
	,{"code" : "30","text" : "封闭车"}
	,{"code" : "31","text" : "罐式车"}
	,{"code" : "32","text" : "厢式车"}
	,{"code" : "33","text" : "自卸车"}
	,{"code" : "34","text" : "棉被车"}
	,{"code" : "99","text" : "其他"}
];

//车长数据
var carLengthJson = [
	 {"code":"4.3","text":"4.3m"}
	,{"code":"5.3","text":"5.3m"}
	,{"code":"6.5","text":"6.5m"}
	,{"code":"6.8","text":"6.8m"}
	,{"code":"7.2","text":"7.2m"}
	,{"code":"7.6","text":"7.6m"}
	,{"code":"8","text":"8m"}
	,{"code":"8.6","text":"8.6m"}
	,{"code":"9.6","text":"9.6m"}
	,{"code":"12.5","text":"12.5m"}
	,{"code":"13","text":"13m"}
	,{"code":"13.5","text":"13.5m"}
	,{"code":"14.5","text":"14.5m"}
	,{"code":"15","text":"15m"}
	,{"code":"16","text":"16m"}
	,{"code":"17","text":"17m"}
	,{"code":"17.5","text":"17.5m"}
	,{"code":"17.8","text":"17.8m"}
	,{"code":"18","text":"18m"}
	,{"code":"18.5","text":"18.5m"}
	,{"code":"21","text":"21m"}
];

//状态数据
var carStatusJson = [
	 {"code":"0","text":"求货","selected":"true"}
	,{"code":"1","text":"已订货"}
	,{"code":"2","text":"半载"}
];
//车牌前缀数据
var licensePlatePrefixJson = [
	 {"code":"粤","text":"粤"}
	,{"code":"桂","text":"桂"}
	,{"code":"湘","text":"湘"}
	,{"code":"鄂","text":"鄂"}
	,{"code":"赣","text":"赣"}
	,{"code":"沪","text":"沪"}
	,{"code":"苏","text":"苏"}
	,{"code":"浙","text":"浙"}
	,{"code":"皖","text":"皖"}
	,{"code":"闽","text":"闽"}
	,{"code":"鲁","text":"鲁"}
	,{"code":"豫","text":"豫"}
	,{"code":"琼","text":"琼"}
	,{"code":"渝","text":"渝"}
	,{"code":"川","text":"川"}
	,{"code":"贵","text":"贵"}
	,{"code":"云","text":"云"}
	,{"code":"京","text":"京"}
	,{"code":"津","text":"津"}
	,{"code":"冀","text":"冀"}
	,{"code":"晋","text":"晋"}
	,{"code":"蒙","text":"蒙"}
	,{"code":"辽","text":"辽"}
	,{"code":"吉","text":"吉"}
	,{"code":"黑","text":"黑"}
	,{"code":"甘","text":"甘"}
	,{"code":"陕","text":"陕"}
	,{"code":"青","text":"青"}
	,{"code":"宁","text":"宁"}
	,{"code":"藏","text":"藏"}
	,{"code":"新","text":"新"}
	,{"code":"港","text":"港"}
	,{"code":"澳","text":"澳"}
	,{"code":"台","text":"台"}
];

//货物类型
var jsonGoodsType = [
	 {"code":"不选择","text":"不选择"}
    ,{"code":"冷冻品","text":"冷冻品"}
	,{"code":"机械设备","text":"机械设备"}
	,{"code":"农副产品","text":"农副产品"}
	,{"code":"医药","text":"医药"}
	,{"code":"零担","text":"零担"}
	,{"code":"整车","text":"整车"}
	,{"code":"重货","text":"重货"}
	,{"code":"日常用品","text":"日常用品"}
	,{"code":"配货","text":"配货"}
	,{"code":"其他","text":"其他"}
];
var jsonGoodsStatus = [
   {"code":"0","text":"未成交"}
	,{"code":"1","text":"已成交"}
	,{"code":"2","text":"录入"}
	,{"code":"3","text":"历史"}
	,{"code":"4","text":"无效"}
];
var jsonAlwaysSend = [
	,{"code":"否","text":"否"}
];
	
//运输类型
var jsonTransportType=[
	 {"code":"整车","text":"整车","selected":true}
	,{"code":"零担","text":"零担"}
	,{"code":"短途","text":"短途"}
];
			
//用户类型
var jsonUserType = [
	 {"code":"2","text":"司机","selected":true}
	,{"code":"1","text":"员工"}
	,{"code":"3","text":"商户"}
];

//会员卡类型
var jsonCardType = [
	 {"code":"0","text":"主卡","selected":true}
	,{"code":"1","text":"副卡"}
];


//系统设置类型
var jsonSystemType = [
	 {"code":"0","text":"数据库","selected":true}
	,{"code":"1","text":"配置文件"}
];

//财务记录类型
var jsonRecondDepart = [
	 {"code":"0","text":"充值","selected":true}
	,{"code":"1","text":"消费"}
	,{"code":"2","text":"退款"}
];

var jsonpayment = [
    {"code":"0","text":"月付"}
    ,{"code":"1","text":"季付"}
    ,{"code":"2","text":"半年"}
    ,{"code":"3","text":"年"}
    ,{"code":"4","text":"其他"}
    ];