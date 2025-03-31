# LibGdxTool3D

3d封装，创建了Asset3D简化加载模型，创建了stage3D,绘制顺序，ui(也就是2d永远在3D上面，参考unity)，鼠标点击，通过发送射线判断点击的是那个。


2d使用，[2d封装](https://github.com/wangGame/LibgdxTool)只读。

## 目标

对libgdx的3d部分进行封装，简化3D的使用。

目前计划使用两个相机，一个2D一个3D两个相机，3D渲染游戏物体  2D渲染ui

## 加载模型

actor的group组使用

```java
BaseActor3D floor = new BaseActor3D(0,0,-0f);
floor.buildModel(200,1,200,false);
floor.setPosition(0,-0.5f,0);
floor.setMaterialTexture(Asset.getAsset().getTexture("img.png"));
```

group使用
```java
PlayerActor playerActor = new PlayerActor(0,0,0);
stage3D.addActor(playerActor);
playerActor.initView();
```

## 动画部分

动画使用

```java
ParticleActor actor = new ParticleActor("effects/gKeyEffect.pfx");
stage3D.addActor(actor);
actor.setPosition(9,0,0);
actor.addAction(Action3Ds.addAction3D(
        Action3Ds.intAction3D(0,100, Interpolation.bounceIn,1)
));
playerActor.addAction(Action3Ds.moveToAction3D(2,2,2,2,Interpolation.linear));

playerActor.addAction(Action3Ds.rotation3D(0,180,180,2,Interpolation.linear));

```

## 增加投影

加入投影效果

