/*************************************************
 ** preload为预加载模块，该文件将会在程序启动时加载 **
 *************************************************/
const Addon = require('ee-core/addon');

/**
 * 预加载模块入口
 */
module.exports = async () => {
  Addon.get('tray').create();
  Addon.get('security').create();
};
