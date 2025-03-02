# LibGdxTool

[![Build Status](https://img.shields.io/github/actions/workflow/status/wangGame/LibGdxTool/gradle.yml?branch=master)](https://github.com/wangGame/LibGdxTool/actions)
[![](https://jitpack.io/v/wangGame/LibGdxTool.svg)](https://jitpack.io/#wangGame/LibGdxTool)


## 添加忽略

```
.gradle/
.idea/
android/build/
core/build/
desktop/build/
libGdxLib/build/
libGdxLib/libGdxLib.iml
local.properties
desktop/desktop.iml
core/core.iml
```

git rm -r -f --cached .

git add .

git commit -m "xx"

## 仓库使用方法

```
maven { url 'https://jitpack.io' }
   
//如果全都要
implementation 'com.github.wangGame:LibGdxTool:pre-release1.0.0'
//只是要部分
// libGdx源码   
implementation 'com.github.wangGame.LibGdxTool:libGdx:pre-release1.0.0'
//自己的工具包
implementation 'com.github.wangGame.LibGdxTool:libGdxLib:pre-release1.0.0'
//desktop快速启动
implementation 'com.github.wangGame.LibGdxTool:desktop:pre-release1.0.0'
```

## 版本说明

- alpha：内部版本
- beta：测试版
- demo：演示版
- enhance：增强版
- free：自由版
- full version：完整版，即正式版
- lts：长期维护版本
- release：发行版
- rc：即将作为正式版发布
- standard：标准版
- ultimate：旗舰版
- upgrade：升级版


## 适配


