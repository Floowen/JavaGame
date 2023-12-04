# PixelRush
![PixelRush-TitleS](https://github.com/Floowen/JavaGame/assets/40651886/ba8c446b-afe1-44e3-bd2a-cb08bf600bcb)

Final project PBO E

## Guidelines
### Levels
-  Level Harus memiliki tinggi 14 tiles/pixel dan minimum 26 tiles/pixel
-  Penamaan file dengan format 1.png, 2.png, 3.png, ....
-  Level Harus ada SpawnPoint player
-  Level Harus memiliki satu enemy

## Level Data
> [!NOTE]
> Entities Hanya Melihat Value Dari Warna Hijau Saja
### Player
-  Spawn point player **harus memiliki value green : 100**
-  Contoh : `rgb(11, 100, 154)`

![1](https://github.com/Floowen/JavaGame/assets/40651886/21fc7124-8094-43b5-ae86-5834b918080e)

### Enemies
-  Crabby **menggunakan value green : 0**
-  Contoh : `rgb(11, 0, 154)`
-  Pinkstar **menggunakan value green : 1**
-  Contoh : `rgb(11, 1, 154)`
-  Shark **menggunakan value green : 2**
-  Contoh : `rgb(11, 2, 154)`

Untuk mengganti value yang dipakai, bisa diganti di Constants.java
  ```
	public static class EnemyConstants {
		public static final int CRABBY = 0;
		public static final int PINKSTAR = 1;
		public static final int SHARK = 2;
```

> [!NOTE]
> Object Level Hanya Melihat Value Dari Warna Biru Saja

### Potions
-  Red Potion **menggunakan value blue : 0**
-  Contoh : `rgb(11, 234, 0)`
-  Blue Potion **menggunakan value blue : 1**
-  Contoh : `rgb(11, 234, 1)`

### Containers
-  Barrel **menggunakan value blue : 2**
-  Contoh : `rgb(11, 234, 2)`
-  Box **menggunakan value blue : 3**
-  Contoh : `rgb(11, 234, 3)`

### Cannons
-  Cannon Left **menggunakan value blue : 5**
-  Contoh : `rgb(11, 234, 5)`
-  Cannon Right **menggunakan value blue : 6**
-  Contoh : `rgb(11, 234, 6)`

Untuk mengganti value yang dipakai, bisa diganti di Constants.java
  ```
public static class ObjectConstants {
		public static final int RED_POTION = 0;
		public static final int BLUE_POTION = 1;
		public static final int BARREL = 2;
		public static final int BOX = 3;
		public static final int SPIKE = 4;
		public static final int CANNON_LEFT = 5;
		public static final int CANNON_RIGHT = 6;
```

## Texture
-  Untuk menambahkan texture/sprite baru, tinggal mengimport gambar kedalam folder /res
-  Tambahkan lokasi texture/sprite di dalam LoadSave.java dengan format :
```
public static final String (NAMA_VARIABEL) = "(LOKASI FOLDER).png"
```
- Tambahkan dimensi texture/sprite di dalam Constants.java di dalam salah satu class yang sesuai (atau buat baru)
```
public static final int (NAMA_VARIABEL)_DEFAULT_WIDTH = (LEBAR SPRITE DALAM PIXEL)
public static final int (NAMA_VARIABEL)_DEFAULT_HEIGHT = (TINGGI SPRITE DALAM PIXEL)

public static final int (NAMA_VARIABEL)_WIDTH = (int) (Game.SCALE * (NAMA_VARIABEL)_DEFAULT_WIDTH);
public static final int (NAMA_VARIABEL)_HEIGHT = (int) (Game.SCALE * (NAMA_VARIABEL)_DEFAULT_HEIGHT);
```
-  Contoh :
 ```
public static final int CANNON_BALL_DEFAULT_WIDTH = 15;
public static final int CANNON_BALL_DEFAULT_HEIGHT = 15;

public static final int CANNON_BALL_WIDTH = (int) (Game.SCALE * CANNON_BALL_DEFAULT_WIDTH);
public static final int CANNON_BALL_HEIGHT = (int) (Game.SCALE * CANNON_BALL_DEFAULT_HEIGHT);
```
