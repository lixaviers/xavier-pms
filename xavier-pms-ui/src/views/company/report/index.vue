<template>
  <div class="report-container">
    <el-tabs v-model="activeTab" type="border-card">
      <!-- 员工信息报表 -->
      <el-tab-pane label="员工信息报表" name="employee">
        <div class="tab-header">
          <span class="tab-title">员工信息统计</span>
          <el-button type="primary" :icon="Download" @click="handleExportEmployee">导出Excel</el-button>
        </div>

        <!-- 统计卡片 -->
        <el-row :gutter="16" class="stat-cards">
          <el-col :span="6">
            <el-card shadow="hover">
              <div class="stat-card">
                <div class="stat-value">{{ employeeData.totalCount || 0 }}</div>
                <div class="stat-label">员工总数</div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="hover">
              <div class="stat-card">
                <div class="stat-value stat-success">{{ employeeData.normalCount || 0 }}</div>
                <div class="stat-label">正式员工</div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="hover">
              <div class="stat-card">
                <div class="stat-value stat-warning">{{ employeeData.pendingCount || 0 }}</div>
                <div class="stat-label">待审批</div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="hover">
              <div class="stat-card">
                <div class="stat-value stat-danger">{{ employeeData.leftCount || 0 }}</div>
                <div class="stat-label">已离职</div>
              </div>
            </el-card>
          </el-col>
        </el-row>

        <!-- 图表区域 -->
        <el-row :gutter="16" class="chart-row">
          <el-col :span="12">
            <el-card shadow="hover">
              <template #header><span>员工状态分布</span></template>
              <div ref="statusChartRef" class="chart-box"></div>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card shadow="hover">
              <template #header><span>性别分布</span></template>
              <div ref="genderChartRef" class="chart-box"></div>
            </el-card>
          </el-col>
        </el-row>

        <el-row :gutter="16" class="chart-row">
          <el-col :span="12">
            <el-card shadow="hover">
              <template #header><span>学历分布</span></template>
              <div ref="degreeChartRef" class="chart-box"></div>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card shadow="hover">
              <template #header><span>部门分布</span></template>
              <div ref="deptChartRef" class="chart-box"></div>
            </el-card>
          </el-col>
        </el-row>

        <el-row :gutter="16" class="chart-row">
          <el-col :span="12">
            <el-card shadow="hover">
              <template #header><span>政治面貌分布</span></template>
              <div ref="politicalChartRef" class="chart-box"></div>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card shadow="hover">
              <template #header><span>婚姻状况分布</span></template>
              <div ref="maritalChartRef" class="chart-box"></div>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>

      <!-- 转正统计报表 -->
      <el-tab-pane label="转正统计报表" name="conversion">
        <div class="tab-header">
          <span class="tab-title">转正统计分析</span>
          <el-button type="primary" :icon="Download" @click="handleExportConversion">导出Excel</el-button>
        </div>

        <!-- 统计卡片 -->
        <el-row :gutter="16" class="stat-cards">
          <el-col :span="6">
            <el-card shadow="hover">
              <div class="stat-card">
                <div class="stat-value stat-warning">{{ conversionData.probationCount || 0 }}</div>
                <div class="stat-label">试用期员工</div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="hover">
              <div class="stat-card">
                <div class="stat-value stat-success">{{ conversionData.convertedCount || 0 }}</div>
                <div class="stat-label">已转正</div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="hover">
              <div class="stat-card">
                <div class="stat-value">{{ conversionData.conversionRate || 0 }}%</div>
                <div class="stat-label">转正率</div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="hover">
              <div class="stat-card">
                <div class="stat-value stat-info">{{ conversionData.thisMonthConversionCount || 0 }}</div>
                <div class="stat-label">本月应转正</div>
              </div>
            </el-card>
          </el-col>
        </el-row>

        <!-- 图表区域 -->
        <el-row :gutter="16" class="chart-row">
          <el-col :span="12">
            <el-card shadow="hover">
              <template #header><span>月度转正趋势</span></template>
              <div ref="conversionTrendChartRef" class="chart-box"></div>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card shadow="hover">
              <template #header><span>试用期时长分布</span></template>
              <div ref="probationPeriodChartRef" class="chart-box"></div>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>

      <!-- 离职统计报表 -->
      <el-tab-pane label="离职统计报表" name="resignation">
        <div class="tab-header">
          <span class="tab-title">离职统计分析</span>
          <el-button type="primary" :icon="Download" @click="handleExportResignation">导出Excel</el-button>
        </div>

        <!-- 统计卡片 -->
        <el-row :gutter="16" class="stat-cards">
          <el-col :span="8">
            <el-card shadow="hover">
              <div class="stat-card">
                <div class="stat-value stat-danger">{{ resignationData.totalCount || 0 }}</div>
                <div class="stat-label">离职总人数</div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover">
              <div class="stat-card">
                <div class="stat-value">{{ resignationData.resignationRate || 0 }}%</div>
                <div class="stat-label">离职率</div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover">
              <div class="stat-card">
                <div class="stat-value stat-warning">{{ resignationData.thisMonthCount || 0 }}</div>
                <div class="stat-label">本月离职</div>
              </div>
            </el-card>
          </el-col>
        </el-row>

        <!-- 图表区域 -->
        <el-row :gutter="16" class="chart-row">
          <el-col :span="12">
            <el-card shadow="hover">
              <template #header><span>离职原因分布</span></template>
              <div ref="resignationReasonChartRef" class="chart-box"></div>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card shadow="hover">
              <template #header><span>部门离职对比</span></template>
              <div ref="deptResignationChartRef" class="chart-box"></div>
            </el-card>
          </el-col>
        </el-row>

        <el-row :gutter="16" class="chart-row">
          <el-col :span="24">
            <el-card shadow="hover">
              <template #header><span>月度离职趋势</span></template>
              <div ref="resignationTrendChartRef" class="chart-box"></div>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick, onBeforeUnmount, watch } from 'vue'
import { Download } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import * as echarts from 'echarts'
import {
  getEmployeeReportApi,
  getConversionReportApi,
  getResignationReportApi,
  exportEmployeeExcelApi,
  exportConversionExcelApi,
  exportResignationExcelApi
} from '@/api/modules/report'
import { saveAs } from 'file-saver'

const activeTab = ref('employee')

// 数据
const employeeData = ref({})
const conversionData = ref({})
const resignationData = ref({})

// 图表实例
const statusChartRef = ref(null)
const genderChartRef = ref(null)
const degreeChartRef = ref(null)
const deptChartRef = ref(null)
const politicalChartRef = ref(null)
const maritalChartRef = ref(null)
const conversionTrendChartRef = ref(null)
const probationPeriodChartRef = ref(null)
const resignationReasonChartRef = ref(null)
const deptResignationChartRef = ref(null)
const resignationTrendChartRef = ref(null)

const chartInstances = []

// 创建饼图
function createPieChart(el, data, title) {
  if (!el) return null
  const chart = echarts.init(el)
  chartInstances.push(chart)
  chart.setOption({
    tooltip: { trigger: 'item', formatter: '{b}: {c} ({d}%)' },
    legend: { bottom: 0, type: 'scroll' },
    series: [{
      type: 'pie',
      radius: ['40%', '70%'],
      avoidLabelOverlap: true,
      itemStyle: { borderRadius: 6, borderColor: '#fff', borderWidth: 2 },
      label: { show: true, formatter: '{b}\n{d}%' },
      data: (data || []).map(item => ({ name: item.name, value: item.count }))
    }]
  })
  return chart
}

// 创建柱状图
function createBarChart(el, data, title, color) {
  if (!el) return null
  const chart = echarts.init(el)
  chartInstances.push(chart)
  const names = (data || []).map(item => item.name)
  const values = (data || []).map(item => item.count)
  chart.setOption({
    tooltip: { trigger: 'axis' },
    grid: { left: '3%', right: '4%', bottom: '15%', containLabel: true },
    xAxis: { type: 'category', data: names, axisLabel: { rotate: names.length > 6 ? 30 : 0 } },
    yAxis: { type: 'value', minInterval: 1 },
    series: [{
      type: 'bar',
      data: values,
      barWidth: '40%',
      itemStyle: { borderRadius: [4, 4, 0, 0], color: color || '#409EFF' },
      label: { show: true, position: 'top' }
    }]
  })
  return chart
}

// 创建折线图
function createLineChart(el, months, values, title, color) {
  if (!el) return null
  const chart = echarts.init(el)
  chartInstances.push(chart)
  chart.setOption({
    tooltip: { trigger: 'axis' },
    grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
    xAxis: { type: 'category', data: months, boundaryGap: false },
    yAxis: { type: 'value', minInterval: 1 },
    series: [{
      type: 'line',
      data: values,
      smooth: true,
      areaStyle: { opacity: 0.15 },
      itemStyle: { color: color || '#409EFF' },
      label: { show: true }
    }]
  })
  return chart
}

// 创建横向柱状图（部门离职对比）
function createHorizontalBarChart(el, data) {
  if (!el) return null
  const chart = echarts.init(el)
  chartInstances.push(chart)
  const names = (data || []).map(item => item.departmentName)
  const totalValues = (data || []).map(item => item.totalCount)
  const resignValues = (data || []).map(item => item.resignationCount)
  chart.setOption({
    tooltip: { trigger: 'axis' },
    legend: { data: ['部门总人数', '离职人数'] },
    grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
    xAxis: { type: 'value', minInterval: 1 },
    yAxis: { type: 'category', data: names },
    series: [
      {
        name: '部门总人数',
        type: 'bar',
        data: totalValues,
        itemStyle: { borderRadius: [0, 4, 4, 0] }
      },
      {
        name: '离职人数',
        type: 'bar',
        data: resignValues,
        itemStyle: { color: '#F56C6C', borderRadius: [0, 4, 4, 0] }
      }
    ]
  })
  return chart
}

// 加载员工信息报表
async function loadEmployeeReport() {
  const res = await getEmployeeReportApi()
  employeeData.value = res || {}
  await nextTick()
  createPieChart(statusChartRef.value, [
    { name: '正式员工', count: res?.normalCount || 0 },
    { name: '待审批', count: res?.pendingCount || 0 },
    { name: '已离职', count: res?.leftCount || 0 }
  ])
  createPieChart(genderChartRef.value, res?.genderDistribution)
  createBarChart(degreeChartRef.value, res?.degreeDistribution, '学历分布', '#67C23A')
  createBarChart(deptChartRef.value, res?.departmentDistribution, '部门分布', '#E6A23C')
  createPieChart(politicalChartRef.value, res?.politicalDistribution)
  createPieChart(maritalChartRef.value, res?.maritalDistribution)
}

// 加载转正统计报表
async function loadConversionReport() {
  const res = await getConversionReportApi()
  conversionData.value = res || {}
  await nextTick()
  const months = (res?.monthlyConversion || []).map(item => item.month)
  const counts = (res?.monthlyConversion || []).map(item => item.count)
  createLineChart(conversionTrendChartRef.value, months, counts, '月度转正趋势', '#67C23A')
  const periodData = (res?.probationPeriodDistribution || []).map(item => ({ name: item.probationPeriod + '个月', count: item.count }))
  createBarChart(probationPeriodChartRef.value, periodData, '试用期时长分布', '#409EFF')
}

// 加载离职统计报表
async function loadResignationReport() {
  const res = await getResignationReportApi()
  resignationData.value = res || {}
  await nextTick()
  createPieChart(resignationReasonChartRef.value, res?.reasonDistribution)
  createHorizontalBarChart(deptResignationChartRef.value, res?.departmentResignation)
  const months = (res?.monthlyTrend || []).map(item => item.month)
  const counts = (res?.monthlyTrend || []).map(item => item.count)
  createLineChart(resignationTrendChartRef.value, months, counts, '月度离职趋势', '#F56C6C')
}

// 导出
async function handleExportEmployee() {
  try {
    const blob = await exportEmployeeExcelApi()
    saveAs(blob, '员工信息报表.xlsx')
    ElMessage.success('导出成功')
  } catch (e) {
    ElMessage.error('导出失败')
  }
}

async function handleExportConversion() {
  try {
    const blob = await exportConversionExcelApi()
    saveAs(blob, '转正统计报表.xlsx')
    ElMessage.success('导出成功')
  } catch (e) {
    ElMessage.error('导出失败')
  }
}

async function handleExportResignation() {
  try {
    const blob = await exportResignationExcelApi()
    saveAs(blob, '离职统计报表.xlsx')
    ElMessage.success('导出成功')
  } catch (e) {
    ElMessage.error('导出失败')
  }
}

// Tab 切换时加载数据
watch(activeTab, (val) => {
  if (val === 'employee') loadEmployeeReport()
  else if (val === 'conversion') loadConversionReport()
  else if (val === 'resignation') loadResignationReport()
})

// 窗口大小变化自适应
function handleResize() {
  chartInstances.forEach(chart => chart?.resize())
}

onMounted(() => {
  loadEmployeeReport()
  window.addEventListener('resize', handleResize)
})

onBeforeUnmount(() => {
  chartInstances.forEach(chart => chart?.dispose())
  window.removeEventListener('resize', handleResize)
})
</script>

<style scoped>
.report-container {
  padding: 16px;
}

.tab-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

.tab-title {
  font-size: 16px;
  font-weight: 600;
  color: #303133;
}

.stat-cards {
  margin-bottom: 16px;
}

.stat-card {
  text-align: center;
  padding: 16px 0;
}

.stat-value {
  font-size: 28px;
  font-weight: 700;
  color: #409EFF;
}

.stat-label {
  font-size: 14px;
  color: #909399;
  margin-top: 8px;
}

.stat-success { color: #67C23A; }
.stat-warning { color: #E6A23C; }
.stat-danger { color: #F56C6C; }
.stat-info { color: #909399; }

.chart-row {
  margin-bottom: 16px;
}

.chart-box {
  height: 320px;
  width: 100%;
}
</style>
