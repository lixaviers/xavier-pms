<template>
  <div :class="classObj" class="app-wrapper" :style="{ '--current-color': theme }">
    <div v-if="device === 'mobile' && sidebar.opened" class="drawer-bg" @click="handleClickOutside" />
    <sidebar class="sidebar-container" />
    <div class="main-container">
      <div>
        <navbar />
        <tags-view />
      </div>
      <app-main />
      <XavierFooter />
    </div>
  </div>
</template>

<script setup>
  import { useWindowSize } from '@vueuse/core';
  import Sidebar from './components/Sidebar/index.vue';
  import { AppMain, Navbar, TagsView, XavierFooter } from './components';

  import useAppStore from '@/store/modules/app';
  import useSettingsStore from '@/store/modules/settings';

  const settingsStore = useSettingsStore();
  const theme = computed(() => settingsStore.theme);
  const sidebar = computed(() => useAppStore().sidebar);
  const device = computed(() => useAppStore().device);

  const classObj = computed(() => ({
    hideSidebar: !sidebar.value.opened,
    openSidebar: sidebar.value.opened,
    withoutAnimation: sidebar.value.withoutAnimation,
    mobile: device.value === 'mobile',
  }));

  const { width } = useWindowSize();
  const WIDTH = 992;

  watchEffect(() => {
    if (device.value === 'mobile' && sidebar.value.opened) {
      useAppStore().closeSideBar({ withoutAnimation: false });
    }
    if (width.value - 1 < WIDTH) {
      useAppStore().toggleDevice('mobile');
      useAppStore().closeSideBar({ withoutAnimation: true });
    } else {
      useAppStore().toggleDevice('desktop');
    }
  });

  function handleClickOutside() {
    useAppStore().closeSideBar({ withoutAnimation: false });
  }
</script>

<style lang="scss" scoped>
  @import '@/assets/styles/mixin.scss';
  @import '@/assets/styles/variables.module.scss';

  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;

    &.mobile.openSidebar {
      position: fixed;
      top: 0;
    }
  }

  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }

  .fixed-header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$base-sidebar-width});
    transition: width 0.28s;
  }

  .hideSidebar .fixed-header {
    width: calc(100% - 54px);
  }

  .sidebarHide .fixed-header {
    width: 100%;
  }

  .mobile .fixed-header {
    width: 100%;
  }
</style>
