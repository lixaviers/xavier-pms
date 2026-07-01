import router from './router'
import { ElMessage } from 'element-plus'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { getToken } from '@/utils/auth'
import { isHttp } from '@/utils/validate'
import { isRelogin } from '@/utils/request'
import useUserStore from '@/store/modules/user'
import useSettingsStore from '@/store/modules/settings'
import usePermissionStore from '@/store/modules/permission'

NProgress.configure({ showSpinner: false })

const whiteList = ['/login', '/register']

router.beforeEach(async (to) => {
  NProgress.start()
  if (getToken()) {
    to.meta.title && useSettingsStore().setTitle(to.meta.title)
    /* has token*/
    if (to.path === '/login') {
      NProgress.done()
      return { path: '/' }
    }
    if (useUserStore().roles.length === 0) {
      isRelogin.show = true
      try {
        // 判断当前用户是否已拉取完user_info信息
        await useUserStore().getInfo()
        isRelogin.show = false
        const accessRoutes = await usePermissionStore().generateRoutes()
        // 根据roles权限生成可访问的路由表
        accessRoutes.forEach((route) => {
          if (!isHttp(route.path)) {
            router.addRoute(route)
          }
        })
        return { ...to, replace: true }
      } catch (err) {
        isRelogin.show = false
        await useUserStore().logOut()
        ElMessage.error(err)
        return { path: '/' }
      }
    }
    return true
  }
  // 没有token
  if (whiteList.indexOf(to.path) !== -1) {
    return true
  }
  NProgress.done()
  return `/login?redirect=${to.fullPath}`
})

router.afterEach(() => {
  NProgress.done()
})
