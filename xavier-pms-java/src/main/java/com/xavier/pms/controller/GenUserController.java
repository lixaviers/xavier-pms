package com.xavier.pms.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.xavier.pms.dto.EmployeeAddDto;
import com.xavier.pms.dto.EmployeeInfoDto;
import com.xavier.pms.dto.EmployeeWorkExperienceDto;
import com.xavier.pms.model.Post;
import com.xavier.pms.model.User;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IPostService;
import com.xavier.pms.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 请求处理层-用户生成管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("genUser")
@RestController
@Api(tags = "用户生成管理接口")
public class GenUserController extends CommonController {

    @Resource
    private IUserService userService;
    @Resource
    private IPostService postService;

    @ApiOperation(value = "生成员工", notes = "生成员工")
    @PutMapping("generate")
    public Result generate() {

        // 董事长
        generate(1L, 1L, 1L);
        // 总经理
        generate(1L, 2L, 2L);
        // 技术总监
        generate(2L, 3L, 3L);
        // 技术副总监
        generate(2L, 4L, 4L);
        generate(2L, 4L, 5L);
        for (int i = 8; i <= 12; i++) {
            int num = random.nextInt(5) + 6;
            for (int i1 = 0; i1 < num; i1++) {
                // 技术经理 6-8随机数
                generate(new Long(i), 5L, new Long(random.nextInt(3) + 6));
            }
            num = random.nextInt(11) + 20;
            for (int i1 = 0; i1 < num; i1++) {
                // 技术主管 20-30随机数
                generate(new Long(i), 6L, 9L);
            }
            num = random.nextInt(101) + 100;
            for (int i1 = 0; i1 < num; i1++) {
                // 开发工程师 100-200随机数
                generate(new Long(i), 7L, new Long(random.nextInt(11) + 10));
            }
            num = random.nextInt(5) + 6;
            for (int i1 = 0; i1 < num; i1++) {
                // 测试经理 6-8随机数
                generate(new Long(i), 8L, new Long(random.nextInt(3) + 6));
            }
            num = random.nextInt(21) + 30;
            for (int i1 = 0; i1 < num; i1++) {
                // 测试工程师 30-50随机数
                generate(new Long(i), 9L, new Long(random.nextInt(11) + 10));
            }
        }

        // 市场总监
        generate(3L, 10L, 3L);
        // 市场副总监
        generate(3L, 11L, 4L);
        generate(3L, 11L, 5L);
        for (int i = 13; i <= 17; i++) {
            int num = random.nextInt(5) + 6;
            for (int i1 = 0; i1 < num; i1++) {
                // 市场经理 6-8随机数
                generate(new Long(i), 12L, new Long(random.nextInt(3) + 6));
            }
            num = random.nextInt(11) + 20;
            for (int i1 = 0; i1 < num; i1++) {
                // 市场主管 20-30随机数
                generate(new Long(i), 13L, 9L);
            }
            num = random.nextInt(101) + 100;
            for (int i1 = 0; i1 < num; i1++) {
                // 市场专员 100-200随机数
                generate(new Long(i), 14L, new Long(random.nextInt(11) + 10));
            }
        }

        // 人事总监
        generate(4L, 15L, 3L);
        // 人事副总监
        generate(4L, 16L, 4L);
        generate(4L, 16L, 5L);
        for (int i = 18; i <= 22; i++) {
            int num = random.nextInt(5) + 6;
            for (int i1 = 0; i1 < num; i1++) {
                // 人事经理 6-8随机数
                generate(new Long(i), 17L, new Long(random.nextInt(3) + 6));
            }
            num = random.nextInt(11) + 20;
            for (int i1 = 0; i1 < num; i1++) {
                // 人事主管 20-30随机数
                generate(new Long(i), 18L, 9L);
            }
            num = random.nextInt(21) + 30;
            for (int i1 = 0; i1 < num; i1++) {
                // 人事专员 30-50随机数
                generate(new Long(i), 19L, new Long(random.nextInt(11) + 10));
            }
            for (int i1 = 0; i1 < num; i1++) {
                // 行政经理 6-8随机数
                generate(new Long(i), 20L, new Long(random.nextInt(3) + 6));
            }
            num = random.nextInt(11) + 20;
            for (int i1 = 0; i1 < num; i1++) {
                // 行政主管 20-30随机数
                generate(new Long(i), 21L, 9L);
            }
            num = random.nextInt(21) + 30;
            for (int i1 = 0; i1 < num; i1++) {
                // 行政专员 30-50随机数
                generate(new Long(i), 22L, new Long(random.nextInt(5) + 16));
            }
        }

        // 财务总监
        generate(5L, 23L, 3L);
        // 财务副总监
        generate(5L, 24L, 4L);
        generate(5L, 24L, 5L);
        for (int i = 23; i <= 27; i++) {
            int num = random.nextInt(5) + 6;
            for (int i1 = 0; i1 < num; i1++) {
                // 财务经理 6-8随机数
                generate(new Long(i), 25L, new Long(random.nextInt(3) + 6));
            }
            num = random.nextInt(11) + 20;
            for (int i1 = 0; i1 < num; i1++) {
                // 财务主管 20-30随机数
                generate(new Long(i), 26L, 9L);
            }
            num = random.nextInt(21) + 30;
            for (int i1 = 0; i1 < num; i1++) {
                // 财务专员 30-50随机数
                generate(new Long(i), 27L, new Long(random.nextInt(5) + 16));
            }
        }

        // 运营总监
        generate(6L, 28L, 3L);
        // 运营副总监
        generate(6L, 29L, 4L);
        generate(6L, 29L, 5L);
        for (int i = 28; i <= 32; i++) {
            int num = random.nextInt(5) + 6;
            for (int i1 = 0; i1 < num; i1++) {
                // 运营经理 6-8随机数
                generate(new Long(i), 30L, new Long(random.nextInt(3) + 6));
            }
            num = random.nextInt(11) + 20;
            for (int i1 = 0; i1 < num; i1++) {
                // 运营主管 20-30随机数
                generate(new Long(i), 31L, 9L);
            }
            num = random.nextInt(101) + 100;
            for (int i1 = 0; i1 < num; i1++) {
                // 运营专员 100-200随机数
                generate(new Long(i), 32L, new Long(random.nextInt(5) + 16));
            }
        }

        // 产品总监
        generate(7L, 33L, 3L);
        // 产品副总监
        generate(7L, 34L, 4L);
        generate(7L, 34L, 5L);
        for (int i = 33; i <= 37; i++) {
            int num = random.nextInt(5) + 6;
            for (int i1 = 0; i1 < num; i1++) {
                // 产品经理 6-8随机数
                generate(new Long(i), 35L, new Long(random.nextInt(3) + 6));
            }
            num = random.nextInt(101) + 100;
            for (int i1 = 0; i1 < num; i1++) {
                // 产品专员 100-200随机数
                generate(new Long(i), 36L, new Long(random.nextInt(5) + 16));
            }
        }

        // 销售总监
        generate(38L, 37L, 3L);
        // 销售副总监
        generate(38L, 38L, 4L);
        generate(38L, 38L, 5L);
        for (int i = 39; i <= 48; i++) {
            int num = random.nextInt(5) + 6;
            for (int i1 = 0; i1 < num; i1++) {
                // 销售经理 6-8随机数
                generate(new Long(i), 39L, new Long(random.nextInt(3) + 6));
            }
            num = random.nextInt(11) + 20;
            for (int i1 = 0; i1 < num; i1++) {
                // 销售主管 20-30随机数
                generate(new Long(i), 40L, 9L);
            }
            num = random.nextInt(101) + 100;
            for (int i1 = 0; i1 < num; i1++) {
                // 销售专员 100-200随机数
                generate(new Long(i), 41L, new Long(random.nextInt(5) + 16));
            }
        }

        return Result.ok();
    }

    private void generate(Long departmentId, Long postId, Long professionalTitleId) {
        EmployeeAddDto dto = new EmployeeAddDto();
        dto.setDocumentType("身份证");
        // 身份证
        dto.setDocumentNumber(generateIDCard());
        dto.setBirthDate(getBirthDate(dto.getDocumentNumber()));
        dto.setGender(getGender(dto.getDocumentNumber()));
        dto.setNickName(generateRandomChineseName(dto.getGender()));
        dto.setMobile(generateRandomPhoneNumber());
        dto.setEmail(generateRandomEmail());

        Long directLeaderId = 0L;
        Post post = postService.getById(postId);
        if (!Objects.equals(post.getParentId(), 0L)) {
            List<User> list = userService.list(User.gw()
                    .eq(User::getPostId, post.getParentId())
                    .eq(User::getDepartmentId, departmentId)
            );
            if (CollUtil.isEmpty(list)) {
                list = userService.list(User.gw().eq(User::getPostId, post.getParentId()));
            }
            if (CollUtil.isNotEmpty(list)) {
                directLeaderId = list.get(random.nextInt(list.size())).getId();
            }
        }
        dto.setDirectLeaderId(directLeaderId);
        LocalDate today = LocalDate.now();
        long days = ChronoUnit.DAYS.between(LocalDate.of(today.getYear(), 1, 1), today) + 1; //+1是为了包括今天
        int num = random.nextInt((int) days);
        dto.setEntryDate(today.plusDays(num * -1));
        dto.setProbationPeriod(3);
        dto.setEstimatedConversionDate(dto.getEntryDate().plusMonths(dto.getProbationPeriod()));
        dto.setDepartmentId(departmentId);
        dto.setPostId(postId);
        dto.setProfessionalTitleId(professionalTitleId);
        dto.setFirstEmploymentDate(dto.getBirthDate().plusYears(20));
        boolean isTrue = random.nextBoolean(); // 随机生成是或否
        dto.setHouseholdRegistrationType(isTrue ? "城镇" : "非城镇");
        dto.setDomicileAddress(getCity(dto.getDocumentNumber()));
        dto.setResidentialAddress(dto.getDomicileAddress());
        dto.setNationality("汉族");
        isTrue = random.nextBoolean(); // 随机生成是或否
        dto.setMaritalStatus(isTrue ? "已婚" : "未婚");
        isTrue = random.nextBoolean(); // 随机生成是或否
        dto.setPoliticalStatus(isTrue ? "群众" : "党员");
        dto.setHighestDegree(getHighestDegree());

        workList = new ArrayList<>();
        generateWorkList(dto.getPostId(), dto.getFirstEmploymentDate(), dto.getEntryDate());

        Collections.reverse(workList);
        dto.setWorkExperienceList(workList);

        List<EmployeeInfoDto> familyInfoList = new ArrayList<>();
        EmployeeInfoDto employeeInfoDto = new EmployeeInfoDto();
        employeeInfoDto.setMobile(generateRandomPhoneNumber());
        employeeInfoDto.setRelation(relations.get(random.nextInt(relations.size())));
        String gender = random.nextBoolean() ? "男" : "女";
        if (Objects.equals(employeeInfoDto.getRelation(), "配偶")) {
            if (Objects.equals(dto.getGender(), "男")) {
                gender = "女";
            } else {
                gender = "男";
            }
        }
        employeeInfoDto.setNickName(generateRandomChineseName(gender));
        familyInfoList.add(employeeInfoDto);
        dto.setFamilyInfoList(familyInfoList);
        dto.setEmergencyContactList(familyInfoList);

        userService.createUser(dto);
    }

    private static final List<String> relations = CollUtil.newArrayList("父母", "子女", "配偶", "兄妹", "其他");

    private static final String[] SURNAME_LIST = {
            "丁", "安", "艾", "白", "张", "王", "孔", "康", "李", "潘", "赵", "陶", "田", "任", "陈", "刘", "吴", "孙", "周", "杨", "朱", "冯", "何", "邹", "熊", "邱", "秦", "江", "尹", "蒋", "崔", "隋", "金", "谢", "魏", "夏", "萧", "叶", "梁", "韩", "秦", "宋", "欧阳", "诸葛", "鄂"};
    private static final String[] MALE_NAME_LIST = {"峰", "坤", "冬", "宁", "岩", "子平", "飞星", "弘深", "元魁", "鸿", "睿好", "修平", "丰茂", "元洲", "立诚", "明远", "彦博", "怡和", "开霁", "圣杰", "磊", "强", "伟", "勇", "俊", "军", "亮", "刚", "桂华", "欢", "佳", "瑞", "玉", "红"};
    private static final String[] FEMALE_NAME_LIST = {"婷", "敏", "娜", "秀英", "慧", "亮", "静", "丽", "桂英", "桂兰", "桂芳", "欢", "娇", "佳", "媛", "玲", "瑞", "欣", "艳", "芳", "芸", "芝", "芹", "玉", "雪", "萍", "红", "莉", "俊英", "俊慧", "俊琪"};

    private static final Random random = new Random();

    public static String generateRandomChineseName(String gender) {
        String surname = SURNAME_LIST[random.nextInt(SURNAME_LIST.length)];
        String name = MALE_NAME_LIST[random.nextInt(MALE_NAME_LIST.length)];
        if ("女".equals(gender)) {
            name = FEMALE_NAME_LIST[random.nextInt(FEMALE_NAME_LIST.length)];
        }
        return surname + name;
    }

    public static String generateRandomPhoneNumber() {
        String[] prefixArray = {"130", "131", "132", "133", "135", "136", "137", "138", "139", "147", "150", "151", "152", "153", "155", "156", "157", "158", "159", "186", "187", "188", "189"};
        String prefix = prefixArray[new Random().nextInt(prefixArray.length)];
        String suffix = "";
        for (int i = 0; i < 8; i++) {
            suffix += String.valueOf(new Random().nextInt(10));
        }
        return prefix + suffix;
    }

    public static String generateRandomEmail() {
        String[] emailSuffixArray = {"@gmail.com", "@yahoo.com", "@163.com", "@qq.com", "@hotmail.com", "@outlook.com"};
        String emailPrefix = "";
        for (int i = 0; i < 8; i++) {
            emailPrefix += String.valueOf((char) (new Random().nextInt(26) + 'a'));
        }
        String emailSuffix = emailSuffixArray[new Random().nextInt(emailSuffixArray.length)];
        return emailPrefix + emailSuffix;
    }

    public static void main(String[] args) {
        String idCard = generateIDCard();
        System.out.println("Generated ID Card: " + idCard);
        System.out.println("Birth Date: " + getBirthDate(idCard));
        String gender = getGender(idCard);
        System.out.println("Gender: " + gender);
    }

    private static final String[] cities = {"110000", "310000", "440100", "440300", "330100", "320500", "320100", "420100", "510100", "500000"};

    private static String generateIDCard() {
        Random random = new Random();
        // 生成生日
        int year = random.nextInt(11) + 1985; // 1985-1995年
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1; // 假设所有月份都有28天

        // 生成城市编码
        String cityCode = cities[random.nextInt(cities.length)];

        // 生成性别和顺序码
        int serialNum = random.nextInt(999) + 1; // 1-999
        int genderNum = random.nextInt(2) + 1; // 1表示男性，2表示女性
        int checkCode = random.nextInt(10); // 校验码，可以随机生成

        // 拼接身份证号
        return String.format("%s%d%02d%02d%03d%1d%d", cityCode, year, month, day, serialNum, genderNum, checkCode);
    }

    private static String getCity(String idCard) {
        String cityCode = idCard.substring(0, 6);
        switch (cityCode) {
            case "110000":
                return "北京市";
            case "310000":
                return "上海市";
            case "440100":
                return "广州市";
            case "440300":
                return "深圳市";
            case "330100":
                return "杭州市";
            case "320500":
                return "苏州市";
            case "320100":
                return "南京市";
            case "420100":
                return "武汉市";
            case "510100":
                return "成都市";
            case "500000":
                return "重庆市";
            default:
                return "";
        }
    }

    private static LocalDate getBirthDate(String idCard) {
        String birthDate = idCard.substring(6, 14);
        return LocalDateTimeUtil.parseDate(birthDate.substring(0, 4) + "-" + birthDate.substring(4, 6) + "-" + birthDate.substring(6), "yyyy-MM-dd");
    }

    private static String getGender(String idCard) {
        int genderNum = Integer.parseInt(idCard.substring(16, 17));
        return genderNum % 2 == 0 ? "女" : "男";
    }

    private static List<EmployeeWorkExperienceDto> workList;

    private void generateWorkList(Long postId, LocalDate start, LocalDate end) {
        long years = ChronoUnit.YEARS.between(start, end);

        EmployeeWorkExperienceDto work = new EmployeeWorkExperienceDto();
        work.setEntryDate(start);
        List<Post> list = postService.list(Post.gw().eq(Post::getParentId, postId));
        if (CollUtil.isNotEmpty(list)) {
            work.setPosition(list.get(random.nextInt(list.size())).getPostName());
        } else {
            Post post = postService.getBasePost(postId);
            work.setPosition(post.getPostName());
        }
        work.setDepartment("xxx");
        work.setCompany(generateCompanyName(random.nextInt(3) + 4));

        if (years > 3) {
            int number = random.nextInt(636) + 365;  // 生成365到1000之间的随机数
            LocalDate temp = start.plusDays(number);
            work.setLeaveOfficeDate(temp);
            workList.add(work);
            generateWorkList(postId, temp.plusDays(random.nextInt(10) + 5), end);
        } else {
            work.setLeaveOfficeDate(end.plusDays((random.nextInt(5) + 5) * -1));
            workList.add(work);
        }
    }


    private static final String[] COMPANY_PREFIXES = {"天", "地", "之", "日", "中", "华", "宏", "盛", "万", "利", "宝", "凯", "鸿", "泰", "合", "兴", "华泰", "电", "快", "巴", "兔", "礼", "鸿"};
    private static final String[] COMPANY_SUFFIXES = {"集团", "公司", "企业", "有限公司", "科技"};

    /**
     * 生成随机中文公司名称
     *
     * @param length 公司名长度，包含前缀和后缀的长度
     * @return 随机生成的中文公司名
     */
    public static String generateCompanyName(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(COMPANY_PREFIXES[random.nextInt(COMPANY_PREFIXES.length)]);
        }
        sb.append(COMPANY_SUFFIXES[random.nextInt(COMPANY_SUFFIXES.length)]);
        return sb.toString();
    }

    private static String getHighestDegree() {
        int num = random.nextInt(4);
        switch (num) {
            case 0:
                return "大专";
            case 1:
                return "本科";
            case 2:
                return "硕士";
            case 3:
                return "博士";
        }
        return "";
    }

}
