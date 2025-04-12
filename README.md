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

### 光照

添加两个灯光：一个环境光，它照亮正在绘制的所有东西（环境的常规光源），以及一个方向光，它有一个方向（最类似于“太阳”类型的光源）。
通常，对于灯光，您可以尝试方向，颜色和不同的类型。另一种类型的灯光（PointLight）可以与手电筒进行比较。

```java
environment.set(new ColorAttribute(ColorAttribute.AmbientLight,0.4f, 0.4f, 0.4f, 1f));
environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f)); 
```

定向光的构造函数后面是一个方向。这个方向可以被看作是一个矢量。

## 基本位置操作

### 运动

平移、旋转和缩放与2D游戏中的等价物略有不同。它们稍微更数学化。更简单的部分是矢量。我们现在将使用Vector 3D而不是Vector 2D，
后者本质上是相同的;它只是增加了另一个维度

```java
Vector3 vector3 = new Vector3();
teaCup.getModelData().transform.getTranslation(vector3);
vector3.x += 3;
teaCup.getModelData().transform.setTranslation(vector3);
```

### 旋转

旋转与2D略有不同，因为我们可以在多个轴上旋转，即x，y和z轴。我们现在将创建一个函数来展示模型的旋转。首先，让我们创建一个函数，在其中我们可以在所有轴上旋转对象：

```java
private void rotate() {
    if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
        instance.transform.rotate(Vector3.X,
                Gdx.graphics.getDeltaTime() * 100);
    if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
        instance.transform.rotate(Vector3.Y,
                Gdx.graphics.getDeltaTime() * 100);
    if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
        instance.transform.rotate(Vector3.Z,
                Gdx.graphics.getDeltaTime() * 100);
} 
```

setToRotation但是会消除之前的旋转，所以可以使用下面的方式进行

```java
teaCup.getModelData().transform.setFromEulerAngles()
```

面临着最后一个问题。我们似乎不能移动立方体！setFromEulerAngles函数清除所有
的translation和rotation属性，setFromEulerAngles返回一个Matrix 4类型，
因此我们可以从它链接并调用另一个函数，例如，一个转换矩阵的函数。为此，我们使用trn（x，y，z）
函数（translate的缩写）。现在我们可以更新我们的旋转函数。

```java
instance.transform.setFromEulerAngles(0, 0, rotation).trn(position.x,position.y, position.z);
上面这个可以完成移动，所以可以删除下面的代码
instance.transform.setTranslation(position); 
```

### 缩放

```java
 instance.transform.setFromEulerAngles(0, 0,
                                       rotation).trn(position.x, position.y,
                                                     position.z).scale(scale,scale,scale);

```

## 显示

屏幕裁剪，检测物体是否在相机的展示范围内

BaseActor3D中,绘制之前判断一下，可以将不会展示的不进行展示

```java
public boolean isCaremaClip(){
    if (stage3D!=null) {
        modelData.transform.getTranslation(positionTmep);
        positionTmep.add(center);
        return stage3D.getCamera().frustum.sphereInFrustum(position, radius);
    }
    return false;
}
```

## 点击事件

```java
public boolean touchDown (int screenX, int screenY, int pointer, int button) {
    rayBean.reset();
    Ray ray = camera.getPickRay(screenX,screenY);
    //这个是root
    getRoot().checkCollisions(ray,rayBean);
    BaseActor3D baseActor3D = rayBean.getBaseActor3D();
    if (rayBean.getBaseActor3D()!=null) {
        baseActor3D.touchUp(rayBean.getVector3(),pointer,button);
        return true;
    }
    return false;
}
```

## unity canvas的几种模式

- overlay 始终在最上层
- camra 他会根据相机的视角  不可以改变x y z
- world  可以改变x y z

本仓库如何使用这类功能
