
# FieldArea

#### property
| 　name   | type    |
|:-----------|------------:||
|field_width|int|
|field_height|int|
|line_color|String|

#### constructor
|name|
|:---|
|initField(int width, int height)|
|initField()|

#### method
| 　name   | type    |explain |
|:-----------|------------:|-------:|
|getFieldSize()|fieldArea|フィールドのwidth,heightを求める|
|getFieldWidth()|int|フィールドのwidthを求める|
|getFieldHeight()|int|フィールドのheightを求める|
|changeLineColor(String color)|void|線の色をセットする|
|drawLine(Point point)|void|現在座標から線を引く|
|drawGridLine()|void|グリッドラインを引く|




# Player

#### property
| 　name   | type    |
|:-----------|------------:||
|point_|Point|
|rotation_|int|
|texture_type|int|
|texture_color|int|

#### constructor
|name|
|:---|
|initPlayer(Point point, int rotation, int textureType)|
|initPlayer(int textureType)|

#### method
| 　name   | type    |explain |
|:-----------|------------:|-------:|
|getPoint()|Point|現在の座標を返す|
|getRotation()|int|現在の角度を返す|
|changeTexColor()|int|オブジェクトの色を変える|
|static changeAllTexColor()|int |すべてのオブジェクトの色を返る|
|move(int amountOfMove)|void|オブジェクトを移動|
|moveWithLine(int amountOfMove)|void|オブジェクトを移動させて、線を引く|
|turn(int degree)|void|オブジェクトの向きを変える|


# Point

#### property
|name|type|
|:--|:---|
|x_|int|
|y_|int|

#### constructor
|name|
|:---|
|initPoint(int x, int y)|


# Calcu

#### method
| 　name   | type    |explain |
|:-----------|------------:|-------:|
|calcuRotation(double rad)|int|ラジアンを角度に変換|
|calcuPoint(Point nowPoint, int rotation, int amountOfMove)|Point|現在座標と移動量から目標座標を計算|
|
