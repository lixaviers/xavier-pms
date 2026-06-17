<template>
  <div class="profile-container">
    <!-- 顶部 Banner -->
    <el-card class="profile-banner" shadow="never">
      <div class="banner-content">
        <div class="banner-left">
          <div class="banner-avatar">
            <userAvatar :user="state.user" />
          </div>
          <div class="banner-info">
            <h2 class="banner-name">{{ state.user.nickName || '—' }}</h2>
            <div class="banner-meta">
              <el-tag :type="state.user.isAdmin ? 'danger' : ''" effect="dark" round>
                {{ state.user.isAdmin ? '管理员' : '普通用户' }}
              </el-tag>
              <span class="meta-divider">|</span>
              <span class="meta-text">{{ state.user.departmentName || '—' }} · {{ state.user.postName || '—' }}</span>
              <span class="meta-divider">|</span>
              <span class="meta-text">工号 {{ state.user.employeeNumber || '—' }}</span>
            </div>
          </div>
        </div>
        <div class="banner-right">
          <el-button type="primary" plain :icon="Lock" @click="pwdDialogVisible = true">修改密码</el-button>
        </div>
      </div>
    </el-card>

    <!-- 详情区 -->
    <div class="profile-sections">
      <!-- 基本信息 -->
      <el-card shadow="hover" class="section-card">
        <template #header>
          <div class="section-title">
            <el-icon :size="18"><User /></el-icon>
            <span>基本信息</span>
          </div>
        </template>
        <div class="field-grid">
          <div class="field-item">
            <span class="field-label">姓名</span>
            <span class="field-value">{{ state.user.nickName || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">别名</span>
            <span class="field-value">{{ state.user.alias || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">手机号</span>
            <span class="field-value">{{ state.user.mobile || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">邮箱</span>
            <span class="field-value">{{ state.user.email || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">入职部门</span>
            <span class="field-value">{{ state.user.departmentName || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">直属上级</span>
            <span class="field-value">{{ state.user.directLeaderId === 0 ? '无（最上级）' : (state.user.directLeaderName || '—') }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">职位</span>
            <span class="field-value">{{ state.user.postName || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">职称</span>
            <span class="field-value">{{ state.user.professionalTitleName || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">入职日期</span>
            <span class="field-value">{{ state.user.entryDate || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">试用期(月)</span>
            <span class="field-value">{{ state.user.probationPeriod ?? '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">预计转正日期</span>
            <span class="field-value">{{ state.user.estimatedConversionDate || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">首次参加工作</span>
            <span class="field-value">{{ state.user.firstEmploymentDate || '—' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 个人信息 -->
      <el-card shadow="hover" class="section-card">
        <template #header>
          <div class="section-title">
            <el-icon :size="18"><Postcard /></el-icon>
            <span>个人信息</span>
          </div>
        </template>
        <div class="field-grid">
          <div class="field-item">
            <span class="field-label">证件类型</span>
            <span class="field-value">{{ state.user.documentType || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">证件号</span>
            <span class="field-value">{{ state.user.documentNumber || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">性别</span>
            <span class="field-value">{{ genderText }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">出生日期</span>
            <span class="field-value">{{ state.user.birthDate || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">民族</span>
            <span class="field-value">{{ state.user.nationality || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">婚姻状况</span>
            <span class="field-value">{{ state.user.maritalStatus || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">政治面貌</span>
            <span class="field-value">{{ state.user.politicalStatus || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">户籍类型</span>
            <span class="field-value">{{ state.user.householdRegistrationType || '—' }}</span>
          </div>
          <div class="field-item field-item--full">
            <span class="field-label">户籍地址</span>
            <span class="field-value">{{ state.user.domicileAddress || '—' }}</span>
          </div>
          <div class="field-item field-item--full">
            <span class="field-label">居住地址</span>
            <span class="field-value">{{ state.user.residentialAddress || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">社保账号</span>
            <span class="field-value">{{ state.user.socialSecurityAccountNumber || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">公积金账号</span>
            <span class="field-value">{{ state.user.providentFundAccount || '—' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 学历信息 -->
      <el-card shadow="hover" class="section-card">
        <template #header>
          <div class="section-title">
            <el-icon :size="18"><Reading /></el-icon>
            <span>学历信息</span>
          </div>
        </template>
        <div class="field-grid">
          <div class="field-item">
            <span class="field-label">最高学历</span>
            <span class="field-value">{{ state.user.highestDegree || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">毕业院校</span>
            <span class="field-value">{{ state.user.graduateSchool || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">所学专业</span>
            <span class="field-value">{{ state.user.major || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">毕业时间</span>
            <span class="field-value">{{ state.user.graduationTime || '—' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 银行卡 -->
      <el-card shadow="hover" class="section-card">
        <template #header>
          <div class="section-title">
            <el-icon :size="18"><CreditCard /></el-icon>
            <span>银行卡</span>
          </div>
        </template>
        <div class="field-grid">
          <div class="field-item">
            <span class="field-label">开户银行</span>
            <span class="field-value">{{ state.user.depositBank || '—' }}</span>
          </div>
          <div class="field-item">
            <span class="field-label">银行卡号</span>
            <span class="field-value">{{ state.user.bankCardNumber || '—' }}</span>
          </div>
        </div>
      </el-card>
    </div>

    <!-- 修改密码弹窗 -->
    <el-dialog v-model="pwdDialogVisible" title="修改密码" width="480px" destroy-on-close>
      <resetPwd @success="pwdDialogVisible = false" />
    </el-dialog>
  </div>
</template>

<script setup name="Profile">
import userAvatar from "./userAvatar";
import resetPwd from "./resetPwd";
import { getProfileApi } from "@/api/modules/user";
import { User, Lock, Postcard, Reading, CreditCard } from '@element-plus/icons-vue'

const state = reactive({
  user: {}
});

const pwdDialogVisible = ref(false)

const genderText = computed(() => {
  const map = { male: '男', female: '女', '1': '男', '0': '女' }
  return map[state.user.gender] || state.user.gender || '—'
})

function getProfile() {
  getProfileApi().then(response => {
    state.user = response || {};
  });
};

getProfile();
</script>

<style lang="scss" scoped>
.profile-container {
  padding: 20px;
}

// 顶部 Banner
.profile-banner {
  margin-bottom: 24px;
  border-radius: 12px;
  background: linear-gradient(135deg, var(--el-color-primary-light-3) 0%, var(--el-color-primary) 100%);

  :deep(.el-card__body) {
    padding: 28px 32px;
  }

  .banner-content {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .banner-left {
    display: flex;
    align-items: center;
    gap: 24px;
  }

  .banner-avatar {
    flex-shrink: 0;

    :deep(.img-circle) {
      width: 80px;
      height: 80px;
      border: 3px solid rgba(255, 255, 255, 0.6);
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
    }

    :deep(.user-info-head) {
      height: 80px;
    }

    :deep(.user-info-head:hover:after) {
      line-height: 80px;
      border-radius: 50%;
    }
  }

  .banner-info {
    .banner-name {
      margin: 0 0 8px;
      font-size: 24px;
      font-weight: 700;
      color: #fff;
      letter-spacing: 1px;
    }

    .banner-meta {
      display: flex;
      align-items: center;
      gap: 12px;
      flex-wrap: wrap;

      .meta-divider {
        color: rgba(255, 255, 255, 0.4);
        font-size: 14px;
      }

      .meta-text {
        font-size: 14px;
        color: rgba(255, 255, 255, 0.85);
      }
    }
  }

  .banner-right {
    :deep(.el-button) {
      background: rgba(255, 255, 255, 0.2);
      border-color: rgba(255, 255, 255, 0.4);
      color: #fff;

      &:hover {
        background: rgba(255, 255, 255, 0.35);
        border-color: rgba(255, 255, 255, 0.6);
      }
    }
  }
}

// 详情区
.profile-sections {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.section-card {
  border-radius: 12px;

  .section-title {
    display: flex;
    align-items: center;
    gap: 8px;
    font-size: 16px;
    font-weight: 600;
    color: var(--el-text-color-primary);
  }

  :deep(.el-card__header) {
    padding: 16px 24px;
    border-bottom: 1px solid var(--el-border-color-lighter);
  }

  :deep(.el-card__body) {
    padding: 24px;
  }
}

// 字段网格
.field-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px 32px;
}

.field-item {
  display: flex;
  flex-direction: column;
  gap: 6px;
  min-width: 0;

  &--full {
    grid-column: 1 / -1;
  }

  .field-label {
    font-size: 13px;
    color: var(--el-text-color-secondary);
    line-height: 1;
  }

  .field-value {
    font-size: 14px;
    color: var(--el-text-color-primary);
    font-weight: 500;
    line-height: 1.5;
    word-break: break-all;
  }
}

// 响应式
@media (max-width: 1200px) {
  .field-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 768px) {
  .field-grid {
    grid-template-columns: 1fr;
  }
}
</style>
