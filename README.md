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

## 模型创建

## 光照

添加两个灯光：一个环境光，它照亮正在绘制的所有东西（环境的常规光源），以及一个方向光，它有一个方向（最类似于“太阳”类型的光源）。
通常，对于灯光，您可以尝试方向，颜色和不同的类型。另一种类型的灯光（PointLight）可以与手电筒进行比较。

```java
environment.set(new ColorAttribute(ColorAttribute.AmbientLight,0.4f, 0.4f, 0.4f, 1f));
environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f)); 
```

定向光的构造函数后面是一个方向。这个方向可以被看作是一个矢量。

## 运动

平移、旋转和缩放与2D游戏中的等价物略有不同。它们稍微更数学化。更简单的部分是矢量。我们现在将使用Vector 3D而不是Vector 2D，后者本质上是相同的;它只是增加了另一个维度

