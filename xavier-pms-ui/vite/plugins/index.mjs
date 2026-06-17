import vue from '@vitejs/plugin-vue'

import createAutoImport from './auto-import.mjs'
import createSvgIcon from './svg-icon.mjs'
import createCompression from './compression.mjs'
import createSetupExtend from './setup-extend.mjs'

export default function createVitePlugins(viteEnv, isBuild = false) {
    const vitePlugins = [vue()]
    vitePlugins.push(createAutoImport())
	vitePlugins.push(createSetupExtend())
    vitePlugins.push(createSvgIcon(isBuild))
	isBuild && vitePlugins.push(...createCompression(viteEnv))
    return vitePlugins
}
