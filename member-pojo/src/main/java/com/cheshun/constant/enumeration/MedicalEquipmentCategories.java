package com.cheshun.constant.enumeration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 器械目录枚举类
 */
public enum MedicalEquipmentCategories {

    SUPER_01("01", "血压监测"),
    SUPER_02("02", "血糖监测"),
    SUPER_03("03", "呼吸治疗"),
    SUPER_04("04", "体温监测"),
    SUPER_05("05", "康复理疗"),
    SUPER_06("06", "医用耗材"),
    SUPER_07("07", "伤口护理"),
    SUPER_08("08", "医疗护具"),
    SUPER_09("09", "康复辅助"),
    SUPER_10("10", "血氧监测"),
    SUPER_11("11", "个人护理"),
    SUPER_12("12", "听力辅助"),
    SUPER_13("13", "听诊器具"),
    SUPER_14("14", "胎心监测"),
    SUPER_15("15", "其他检测"),
    SUPER_16("16", "监测器具"),

    CHILD_0101("0101", "臂式电子血压计"),
    CHILD_0201("0201", "血糖套装"),
    CHILD_0202("0202", "血糖试纸"),
    CHILD_0203("0203", "血糖仪"),
    CHILD_0301("0301", "雾化器"),
    CHILD_0302("0302", "制氧机"),
    CHILD_0303("0303", "氧气袋"),
    CHILD_0304("0304", "呼吸机"),
    CHILD_0305("0305", "吸痰器"),
    CHILD_0401("0401", "玻璃体温计"),
    CHILD_0402("0402", "红外体温计"),
    CHILD_0403("0403", "电子体温计"),
    CHILD_0501("0501", "理疗贴"),
    CHILD_0502("0502", "冰袋"),
    CHILD_0503("0503", "颈椎治疗仪"),
    CHILD_0504("0504", "颈部按摩仪"),
    CHILD_0505("0505", "烤灯"),
    CHILD_0506("0506", "拔罐器"),
    CHILD_0507("0507", "腰部按摩仪"),
    CHILD_0508("0508", "刮痧板"),
    CHILD_0601("0601", "胰岛素针头"),
    CHILD_0602("0602", "笔式注射器"),
    CHILD_0603("0603", "一次性注射器"),
    CHILD_0604("0604", "液体报警器"),
    CHILD_0605("0605", "疝气带"),
    CHILD_0606("0606", "采血针"),
    CHILD_0701("0701", "护理垫"),
    CHILD_0702("0702", "褥疮垫"),
    CHILD_0703("0703", "造口袋"),
    CHILD_0704("0704", "引流袋"),
    CHILD_0801("0801", "静脉曲张袜"),
    CHILD_0802("0802", "护腰"),
    CHILD_0803("0803", "护膝"),
    CHILD_0804("0804", "颈枕"),
    CHILD_0805("0805", "颈托"),
    CHILD_0806("0806", "护踝"),
    CHILD_0807("0807", "足浴盆"),
    CHILD_0808("0808", "护腕"),
    CHILD_0809("0809", "护肘"),
    CHILD_0810("0810", "护臂"),
    CHILD_0811("0811", "矫姿带"),
    CHILD_0812("0812", "护胸"),
    CHILD_0813("0813", "护掌"),
    CHILD_0814("0814", "头部按摩器"),
    CHILD_0901("0901", "拐杖"),
    CHILD_0902("0902", "手动轮椅车"),
    CHILD_0903("0903", "助行架"),
    CHILD_0904("0904", "电动轮椅车"),
    CHILD_1004("1004", "血氧仪"),
    CHILD_1101("1101", "坐便椅"),
    CHILD_1102("1102", "接尿器"),
    CHILD_1103("1103", "便盆"),
    CHILD_1104("1104", "便携药盒"),
    CHILD_1105("1105", "坐厕椅"),
    CHILD_1201("1201", "飞利浦助听器"),
    CHILD_1202("1202", "掌护助听器"),
    CHILD_1301("1301", "听诊器"),
    CHILD_1401("1401", "胎心仪"),
    CHILD_1501("1501", "HIV抗体检测试剂"),
    CHILD_1502("1502", "尿酸试纸"),
    CHILD_1503("1503", "胃幽门螺杆菌检测试纸"),
    CHILD_1504("1504", "尿液检测试纸"),
    CHILD_1505("1505", "尿酸测试仪套装"),
    CHILD_1506("1506", "甲型/乙型流感病毒抗原检测试剂"),
    CHILD_1601("1601", "室内温湿度计")
     ;

    private String categoryCode;
    private String categoryName;

    public static final Map<String, String> map = new HashMap<String, String>();

    static{
        for(MedicalEquipmentCategories test : MedicalEquipmentCategories.values()){
            map.put(test.getCode(), test.getName());
        }
    }

    private MedicalEquipmentCategories(String categoryCode, String categoryName) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }
    public String getCode() {
        return categoryCode;
    }
    public String getName() {
        return categoryName;
    }

    public static String getCodeByName(String name){
        for (MedicalEquipmentCategories value : MedicalEquipmentCategories.values()) {
            if (value.getName().equals(name)){
                return value.getCode();
            }
        }
        return null;
    }

    /**
     * 转为数据
     * @return 枚举对象数组
     */
    public static List<Map<String, String>> toList() {
        List<Map<String, String>> list = new ArrayList<>();
        for (MedicalEquipmentCategories item : MedicalEquipmentCategories.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("code", item.getCode());
            map.put("name", item.getName());
            list.add(map);
        }
        return list;
    }

}
