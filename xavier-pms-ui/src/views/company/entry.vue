<template>
  <div>
    <div v-if="type === 1">
      <div class="app-container employee-entry">
        <el-form
          ref="formRef"
          :model="dataForm"
          :rules="rules"
          label-width="120px"
          style="width: 1000px; padding: 0 0 20px 20px"
        >
          <el-collapse v-model="activeNames" class="mt20">
            <el-collapse-item name="1">
              <template #title>
                <div>基本信息</div>
              </template>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="姓名" prop="nickName">
                    <el-input
                      v-model="dataForm.nickName"
                      placeholder="请输入"
                      maxlength="50"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="部门" prop="departmentId">
                    <el-tree-select
                      v-model="dataForm.departmentId"
                      :data="departmentList"
                      :props="{
                        value: 'id',
                        label: 'deptName',
                        children: 'children'
                      }"
                      value-key="id"
                      placeholder="请选择"
                      check-strictly
                      style="width: 100%"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="职位" prop="postId">
                    <el-select v-model="dataForm.postId" style="width: 100%">
                      <el-option
                        v-for="item in postList"
                        :key="'post-' + item.id"
                        :value="item.id"
                        :label="item.postName"
                      />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="职称" prop="professionalTitleId">
                    <el-input
                      v-model="dataForm.professionalTitleId"
                      placeholder="请输入"
                      maxlength="100"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="手机号" prop="mobile">
                    <el-input
                      v-model="dataForm.mobile"
                      placeholder="请输入"
                      maxlength="15"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="邮箱" prop="email">
                    <el-input
                      v-model="dataForm.email"
                      placeholder="请输入"
                      maxlength="100"
                    />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-collapse-item>
            <el-collapse-item name="2">
              <template #title>
                <div>个人信息</div>
              </template>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="证件类型" prop="documentType">
                    <el-select
                      v-model="dataForm.documentType"
                      style="width: 100%"
                    >
                      <el-option value="身份证" label="身份证" />
                      <el-option value="护照" label="护照" />
                      <el-option value="军官证" label="军官证" />
                      <el-option value="台胞证" label="台胞证" />
                      <el-option value="其他" label="其他" />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="证件号" prop="documentNumber">
                    <el-input
                      v-model="dataForm.documentNumber"
                      placeholder="请输入"
                      maxlength="30"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="出生日期" prop="birthDate">
                    <el-input
                      v-model="dataForm.birthDate"
                      placeholder="请输入"
                      maxlength="100"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item
                    label="户籍类型"
                    prop="householdRegistrationType"
                  >
                    <el-select
                      v-model="dataForm.householdRegistrationType"
                      style="width: 100%"
                    >
                      <el-option value="城镇" label="城镇" />
                      <el-option value="非城镇" label="非城镇" />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="24">
                  <el-form-item label="户籍地址" prop="domicileAddress">
                    <el-input
                      type="textarea"
                      v-model="dataForm.domicileAddress"
                      placeholder="请输入"
                      maxlength="200"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="性别" prop="gender">
                    <el-select v-model="dataForm.gender" style="width: 100%">
                      <el-option value="男" label="男" />
                      <el-option value="女" label="女" />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="民族" prop="nationality">
                    <el-select
                      v-model="dataForm.nationality"
                      filterable
                      style="width: 100%"
                    >
                      <el-option
                        v-for="item in nationalityList"
                        :key="item"
                        :value="item"
                        :label="item"
                      />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="婚姻状况" prop="maritalStatus">
                    <el-select
                      v-model="dataForm.maritalStatus"
                      style="width: 100%"
                    >
                      <el-option value="未婚" label="未婚" />
                      <el-option value="已婚" label="已婚" />
                      <el-option value="已婚已育" label="已婚已育" />
                      <el-option value="离异" label="离异" />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="政治面貌" prop="politicalStatus">
                    <el-select
                      v-model="dataForm.politicalStatus"
                      style="width: 100%"
                    >
                      <el-option value="团员" label="团员" />
                      <el-option value="党员" label="党员" />
                      <el-option value="群众" label="群众" />
                      <el-option value="其他" label="其他" />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item
                    label="社保账号"
                    prop="socialSecurityAccountNumber"
                  >
                    <el-input
                      v-model="dataForm.socialSecurityAccountNumber"
                      placeholder="请输入"
                      maxlength="30"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="公积金账号" prop="providentFundAccount">
                    <el-input
                      v-model="dataForm.providentFundAccount"
                      placeholder="请输入"
                      maxlength="30"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="24">
                  <el-form-item label="居住地址" prop="residentialAddress">
                    <el-input
                      type="textarea"
                      v-model="dataForm.residentialAddress"
                      placeholder="请输入"
                      maxlength="200"
                    />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-collapse-item>
            <el-collapse-item name="3">
              <template #title>
                <div>学历信息</div>
              </template>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="最高学历" prop="highestDegree">
                    <el-select
                      v-model="dataForm.highestDegree"
                      style="width: 100%"
                    >
                      <el-option value="博士" label="博士" />
                      <el-option value="硕士" label="硕士" />
                      <el-option value="本科" label="本科" />
                      <el-option value="大专" label="大专" />
                      <el-option value="中专" label="中专" />
                      <el-option value="高中" label="高中" />
                      <el-option value="初中" label="初中" />
                      <el-option value="小学" label="小学" />
                      <el-option value="其他" label="其他" />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="毕业院校" prop="graduateSchool">
                    <el-input
                      v-model="dataForm.graduateSchool"
                      placeholder="请输入"
                      maxlength="100"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="毕业时间" prop="graduationTime">
                    <el-input
                      v-model="dataForm.graduationTime"
                      placeholder="请输入"
                      maxlength="100"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="所学专业" prop="major">
                    <el-input
                      v-model="dataForm.major"
                      placeholder="请输入"
                      maxlength="100"
                    />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-collapse-item>
            <el-collapse-item name="4">
              <template #title>
                <div>银行卡</div>
              </template>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="开户银行" prop="depositBank">
                    <el-input
                      v-model="dataForm.depositBank"
                      placeholder="请输入"
                      maxlength="100"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="银行卡号" prop="bankCardNumber">
                    <el-input
                      v-model="dataForm.bankCardNumber"
                      placeholder="请输入"
                      maxlength="100"
                    />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-collapse-item>
            <el-collapse-item name="5">
              <template #title>
                <div>家庭信息(最多5条)</div>
              </template>
              <el-table :data="familyInfoList">
                <el-table-column label="" width="45">
                  <template #default="scope">
                    <el-button
                      v-if="familyInfoList.length > 1"
                      @click="handleDelete('family', scope.$index)"
                      type="danger"
                      icon="Delete"
                      circle
                    />
                  </template>
                </el-table-column>
                <el-table-column label="家庭成员姓名">
                  <template #default="scope">
                    <el-input v-model="scope.row.nickName" maxlength="50" />
                  </template>
                </el-table-column>
                <el-table-column label="家庭成员关系">
                  <template #default="scope">
                    <el-select v-model="scope.row.relation" style="width: 100%">
                      <el-option value="父母" label="父母" />
                      <el-option value="子女" label="子女" />
                      <el-option value="配偶" label="配偶" />
                      <el-option value="兄妹" label="兄妹" />
                      <el-option value="其他" label="其他" />
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column label="联系方式">
                  <template #default="scope">
                    <el-input v-model="scope.row.mobile" maxlength="11" />
                  </template>
                </el-table-column>
              </el-table>
              <div v-if="familyInfoList.length < 5" class="mt10">
                <el-button icon="Plus" @click="handleAdd('family')"
                  >增加</el-button
                >
              </div>
            </el-collapse-item>
            <el-collapse-item name="6">
              <template #title>
                <div>紧急联系人(最多5条)</div>
              </template>
              <el-table :data="emergencyContactList">
                <el-table-column label="" width="45">
                  <template #default="scope">
                    <el-button
                      v-if="emergencyContactList.length > 1"
                      @click="handleDelete('emergencyContact', scope.$index)"
                      type="danger"
                      icon="Delete"
                      circle
                    />
                  </template>
                </el-table-column>
                <el-table-column label="联系人姓名">
                  <template #default="scope">
                    <el-input v-model="scope.row.nickName" maxlength="50" />
                  </template>
                </el-table-column>
                <el-table-column label="联系人关系">
                  <template #default="scope">
                    <el-select v-model="scope.row.relation" style="width: 100%">
                      <el-option value="父母" label="父母" />
                      <el-option value="子女" label="子女" />
                      <el-option value="配偶" label="配偶" />
                      <el-option value="兄妹" label="兄妹" />
                      <el-option value="朋友" label="朋友" />
                      <el-option value="其他" label="其他" />
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column label="联系方式">
                  <template #default="scope">
                    <el-input v-model="scope.row.mobile" maxlength="11" />
                  </template>
                </el-table-column>
              </el-table>
              <div v-if="emergencyContactList.length < 5" class="mt10">
                <el-button icon="Plus" @click="handleAdd('emergencyContact')"
                  >增加</el-button
                >
              </div>
            </el-collapse-item>
            <el-collapse-item name="7">
              <template #title>
                <div>个人材料(无文件服务器，未实现)</div>
              </template>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="身份证-人面像">
                    <el-input
                      v-model="dataForm.deptName"
                      placeholder="无文件服务器，未实现"
                      disabled
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="身份证-国徽面">
                    <el-input
                      v-model="dataForm.deptName"
                      placeholder="无文件服务器，未实现"
                      disabled
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="学历证书">
                    <el-input
                      v-model="dataForm.deptName"
                      placeholder="无文件服务器，未实现"
                      disabled
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="学位证书">
                    <el-input
                      v-model="dataForm.deptName"
                      placeholder="无文件服务器，未实现"
                      disabled
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="前公司离职证明">
                    <el-input
                      v-model="dataForm.deptName"
                      placeholder="无文件服务器，未实现"
                      disabled
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="员工照片">
                    <el-input
                      v-model="dataForm.deptName"
                      placeholder="无文件服务器，未实现"
                      disabled
                    />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-collapse-item>
            <el-collapse-item name="8">
              <template #title>
                <div>入职申明与签名</div>
              </template>
              <el-row>
                <el-col :span="24">
                  <el-form-item label="入职申明">
                    <el-input
                      type="textarea"
                      placeholder="无文件服务器，未实现"
                      disabled
                      value="一、本人向公司出示的、陈述的任何有关本人自身情况的说明都是真实的。本人自身情况包括但不限于本人的身份证件、学历、学位、技能水平、所获证照，工作经历、家庭情况、婚姻状况、身体状况等。若本人就上述情形做虚假陈述，则视为本人欺诈订立劳动合同，公司可以据此解除与本人的劳动合同，并且不负担任何赔偿责任。因本人的不实陈述给公司造成的损害，由本人承担，并依法做出相应经济赔偿。因本人通讯地址错误或变更后未及时通知公司的，所造成的一切后果，本人承担。
二、本人与前任职单位/雇主不存在任何劳动关系。并且，本人受聘于公司不会违反本人对前任职单位/雇主的任何竞业限制义务，公司不会因雇佣本人而引发任何诉讼。任何公司因雇佣本人而引发的一切法律责任由本人承担。
三、本人承诺不将任何涉及第三方的商业秘密带入公司，并不在公司使用。任何因本人违反对第三方的保守商业秘密的义务而导致的任何法律责任，由本人承担。"
                      :rows="8"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="本人签名">
                    <el-input v-model="dataForm.deptName" disabled />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="签署日期">
                    <el-input v-model="dataForm.deptName" disabled />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-collapse-item>
          </el-collapse>
        </el-form>
      </div>
      <el-affix position="bottom" :offset="0">
        <div class="submit-affix">
          <el-button
            :loading="loading"
            type="primary"
            @click="submitForm"
            size="large"
            style="width: 100px"
            >提 交</el-button
          >
        </div>
      </el-affix>
    </div>

    <el-result v-else icon="success" title="录入成功" sub-title="请等待审核">
      <template #extra>
        <el-button @click="handleBack" type="primary">继续录入</el-button>
      </template>
    </el-result>
  </div>
</template>

<script setup>
import { addOrUpdateUserApi } from '@/api/company/user'
import { queryDepartmentApi } from '@/api/company/dept'
import { queryPostApi } from '@/api/company/post'

const { proxy } = getCurrentInstance()
const data = reactive({
  dataForm: {},
  rules: {
    nickName: [{ required: true, message: '请输入', trigger: 'blur' }],
    mobile: [{ required: true, message: '请输入', trigger: 'blur' }],
    departmentId: [{ required: true, message: '请选择', trigger: 'change' }],
    postId: [{ required: true, message: '请选择', trigger: 'change' }],
    documentType: [{ required: true, message: '请选择', trigger: 'change' }],
    documentNumber: [{ required: true, message: '请输入', trigger: 'blur' }],
    birthDate: [{ required: true, message: '请选择', trigger: 'change' }],
    householdRegistrationType: [
      { required: true, message: '请选择', trigger: 'blur' }
    ],
    domicileAddress: [{ required: true, message: '请输入', trigger: 'blur' }],
    gender: [{ required: true, message: '请选择', trigger: 'change' }],
    nationality: [{ required: true, message: '请选择', trigger: 'change' }],
    maritalStatus: [{ required: true, message: '请选择', trigger: 'blur' }],
    politicalStatus: [{ required: true, message: '请选择', trigger: 'blur' }],
    residentialAddress: [{ required: true, message: '请输入', trigger: 'blur' }]
  }
})

const { dataForm, rules } = toRefs(data)
const loading = ref(false)
const type = ref(1)
const activeNames = ref(['1', '2', '3', '4', '5', '6', '8'])
// 部门列表
const departmentList = ref([])
// 职位列表
const postList = ref([])
// 家庭信息列表
const familyInfoList = ref([{}])
// 紧急联系人列表
const emergencyContactList = ref([{}])
// 民族列表
const nationalityList = ref([
  '汉族',
  '阿昌族',
  '白族',
  '保安族',
  '布朗族',
  '布依族',
  '朝鲜族',
  '穿青人',
  '傣族',
  '达斡尔族',
  '德昂族',
  '侗族',
  '东乡族',
  '独龙族',
  '鄂伦春族',
  '俄罗斯族',
  '鄂温克族',
  '高山族',
  '亻革家人',
  '仡佬族',
  '哈尼族',
  '哈萨克族',
  '赫哲族',
  '回族',
  '景颇族',
  '京族',
  '基诺族',
  '柯尔克孜族',
  '拉祜族',
  '黎族',
  '傈僳族',
  '珞巴族',
  '满族',
  '毛南族',
  '蒙古族',
  '门巴族',
  '苗族',
  '仫佬族',
  '纳西族',
  '怒族',
  '普米族',
  '羌族',
  '撒拉族',
  '畲族',
  '水族',
  '塔吉克族',
  '塔塔尔族',
  '土家族',
  '土族',
  '佤族',
  '维吾尔族',
  '乌孜别克族',
  '锡伯族',
  '瑶族',
  '彝族',
  '裕固族',
  '藏族',
  '壮族'
])

/**
 * 提交
 */
function submitForm() {
  proxy.$refs['formRef'].validate((valid) => {
    if (valid) {
      let list = familyInfoList.value.filter((item) => {
        if (!item.nickName || !item.relation || !item.mobile) {
          return true
        }
        return false
      })
      if (list && list.length > 0) {
        proxy.$modal.msgWarning('请完善家庭信息')
        return
      }
      list = emergencyContactList.value.filter((item) => {
        if (!item.nickName || !item.relation || !item.mobile) {
          return true
        }
        return false
      })
      if (list && list.length > 0) {
        proxy.$modal.msgWarning('请完善紧急联系人')
        return
      }
      loading.value = true
      dataForm.value.familyInfoList = familyInfoList.value
      dataForm.value.emergencyContactList = emergencyContactList.value
      addOrUpdateUserApi(dataForm.value)
        .then((response) => {
          type.value = 2
        })
        .finally(() => {
          loading.value = false
        })
    }
  })
}

/**
 * 增加表格行
 */
function handleAdd(type) {
  if (type === 'family') {
    familyInfoList.value.push({})
  } else if (type === 'emergencyContact') {
    emergencyContactList.value.push({})
  }
}

/**
 * 删除表格行
 */
function handleDelete(type, index) {
  proxy.$modal
    .confirm('是否确认删除?')
    .then(function () {
      if (type === 'family') {
        familyInfoList.value.splice(index, 1)
      } else if (type === 'emergencyContact') {
        emergencyContactList.value.splice(index, 1)
      }
    })
    .catch(() => {})
}

function handleBack() {
  type.value = 1
  dataForm.value = {}
  familyInfoList.value = []
  emergencyContactList.value = []
}

/**
 * 查询职位列表
 */
async function getPostList() {
  const { records } = await queryPostApi({ pageSize: 10000 })
  postList.value = records
}

async function getDepartmentList() {
  const { records } = await queryDepartmentApi({ pageSize: 10000 })
  departmentList.value = proxy.handleTree(records, 'id')
}

onMounted(async () => {
  await getDepartmentList()
  await getPostList()
})
</script>

<style lang="scss">
.submit-affix {
  width: 100%;
  background: #fff;
  text-align: center;
  padding: 20px 0;
  box-shadow: 0 -3px 5px #eee;
}

.employee-entry {
  .el-table {
    .cell {
      overflow: inherit;
    }
  }
}
</style>
