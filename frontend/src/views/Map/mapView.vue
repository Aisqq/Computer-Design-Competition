<template>
  <div ref="container" class="label" id="tagRendererContainer" style="width: 1314px; height: 568px;">
  </div>
</template>

<script>
import * as THREE from 'three';
import {GLTFLoader} from 'three/examples/jsm/loaders/GLTFLoader.js';
import {OrbitControls} from 'three/examples/jsm/controls/OrbitControls.js';
import {CSS2DRenderer, CSS2DObject} from "three/examples/jsm/renderers/CSS2DRenderer.js";
import * as TWEEN from 'tween.js';

var scene = new THREE.Scene();
var camera = null;
// var renderer = null;
// var ambientLight = null;
// var controls = null;
// var tagRenderer = null;

export default {
  data() {
    return {
      zones: [
        // {name: '热轧机区域', position: {x: -190, y: 0, z: -250}, size: {width: 500, height: 40}, color: 0xff0000},
        {name: '熔铸炉区域', position: {x: 30, y: 0, z: -190}, size: {width: 100, height: 60}, color: 0xff0000},
        // {name: '步进炉区域', position: {x: -70, y: 0, z: -190}, size: {width: 50, height: 90}, color: 0xff0000},
        // {name: '双面铣洗机区域', position: {x: -280, y: 0, z: -180}, size: {width: 280, height: 40}, color: 0xff0000},
        {name: '退火炉区域', position: {x: -370, y: 0, z:-60}, size: {width: 100, height: 100}, color: 0xff0000},
        // {name: '冷轧机区域', position: {x: -190, y: 0, z: -60}, size: {width: 150, height: 100}, color: 0xff0000},
        // {name: '清洗机区域', position: {x: -280, y: 0, z: 50}, size: {width: 280, height: 40}, color: 0xff0000},
        // {name: '精剪机区域', position: {x: 50, y: 0, z: 50}, size: {width: 90, height: 40}, color: 0xff0000},
      ],
      options: [
        {label: '熔铸炉 设备进度：80% 设备温度：2000℃', position: {x: 300, y: 0, z: -190}},
        {label: '热轧机 生产进度：70% 设备温度：500℃', position: {x: -300, y: 0, z: -220}},
        {label: '步进炉 生产进度：60% 设备温度：1000℃', position: {x: 200, y: 0, z: -30}},
        {label: '双面铣洗机 生产进度：30%', position: {x: -250, y: 0, z: -30}},
        {label: '退火炉 生产进度：50% 设备温度：500℃', position: {x: -330, y: 0, z: 130}},
        {label: '冷轧机 生产进度：50%', position: {x: -70, y: 0, z: 130}},
        {label: '停车区', position: {x: 200, y: 0, z: 130}},
        {label: '成品区', position: {x: 400, y: 0, z: 130}},
        {label: '清洗机 生产进度：30%', position: {x: -250, y: 0, z: 300}},
        {label: '精剪机 生产进度：60%', position: {x: 250, y: 0, z: 300}},
      ],
    };
  },
  mounted() {
    this.initScene();
    this.loadModel();
    window.addEventListener('resize', this.onWindowResize);
  },
  methods: {
    initScene() {
      this.scene = new THREE.Scene();

      // 加载背景图片
      const textureLoader = new THREE.TextureLoader();
      const texture = textureLoader.load('/module/backg.png');

      // 设置场景的背景为背景图片
      this.scene.background = texture;

      this.camera = new THREE.PerspectiveCamera(100, window.innerWidth / window.innerHeight, 20, 1000);
      this.camera.position.set(0, 260, 0);
      this.camera.lookAt(0, 0, 0);

      this.renderer = new THREE.WebGLRenderer({antialias: true, alpha: true});
      this.renderer.setSize(window.innerWidth, window.innerHeight);
      this.$refs.container.appendChild(this.renderer.domElement);

      const axesHelper = new THREE.AxesHelper(5);
      this.scene.add(axesHelper);

      this.controls = new OrbitControls(this.camera, this.renderer.domElement);
      this.controls.enableDamping = true;
      this.controls.dampingFactor = 0.09;

      this.ambientLight = new THREE.AmbientLight(0xffffff, 0.5);
      this.scene.add(this.ambientLight);

      const directionalLight = new THREE.DirectionalLight(0xffffff, 1);
      directionalLight.position.set(0, 40);
      this.scene.add(directionalLight);

      this.tagRenderer = new CSS2DRenderer();
      // this.tagRenderer.setSize(window.innerWidth, window.innerHeight); // 根据你的容器设置大小
      // 获取DOM元素作为渲染器容器
      const container = document.getElementById('tagRendererContainer');
      // 使用这个DOM元素作为渲染器容器
      this.tagRenderer.setSize(container.clientWidth, container.clientHeight);
      this.tagRenderer.domElement.style.position = "absolute";
      this.tagRenderer.domElement.style.yIndex = 9; // 确保它在最上面
      this.tagRenderer.domElement.style.top = "0px";
      this.tagRenderer.domElement.style.pointerEvents = "none"; // 防止与 3D 交互冲突
      this.$refs.container.appendChild(this.tagRenderer.domElement); // 附加到你的 3D 容器

      this.animate();
    },
    loadModel() {
      const gltfLoader = new GLTFLoader();
      const scene = this.scene; // 将this.scene存储在局部变量以供内部函数访问

      gltfLoader.load("/module/map1.glb", (gltf1) => {
        const baseModel = gltf1.scene;
        baseModel.position.set(-150, 0, -70);
        scene.add(baseModel);
        // 逆时针旋转90°
        gltf1.scene.rotation.y = Math.PI / 2; // 绕Y轴旋转

        gltfLoader.load("/module/car2.glb", (gltf2) => {
          const movingModel = gltf2.scene;
          movingModel.position.set(-440, 0, -130);
          scene.add(movingModel);
          // 逆时针旋转90°
          gltf2.scene.rotation.y = Math.PI / 2; // 绕Y轴旋转
          const points = [
            new THREE.Vector3(-90, 0, -130),
            new THREE.Vector3(-90, 0, 5),
            new THREE.Vector3(-290, 0, 5),
            new THREE.Vector3(-290, 0, -130),
            new THREE.Vector3(-90, 0, -130),
            new THREE.Vector3(-90, 0, 5),
            new THREE.Vector3(-40, 0, 5),
            new THREE.Vector3(140, 0, 5),
            new THREE.Vector3(140, 0, -130),
            new THREE.Vector3(-290, 0, -130),
            new THREE.Vector3(-290, 0, 5),
            new THREE.Vector3(-90, 0, 5),
          ];

          //创建标签
          this.options.forEach((item) => {
            if (item.position) {
              let domElement = document.createElement("div");
              domElement.className = "label";
              domElement.innerHTML = `<div class="label">${item.label}</div>`;

              let labelObject = new CSS2DObject(domElement);
              labelObject.position.set(item.position.x, item.position.y, item.position.z);
              this.scene.add(labelObject);
            }
          });
          // 加载模型后，添加平铺的区域
          this.zones.forEach(zone => {
            const geometry = new THREE.PlaneGeometry(zone.size.width, zone.size.height);
            const material = new THREE.MeshBasicMaterial({color: zone.color, side: THREE.DoubleSide});
            const mesh = new THREE.Mesh(geometry, material);
            mesh.position.set(zone.position.x, 0, zone.position.z);
            mesh.rotation.x = -Math.PI / 2;
            scene.add(mesh);
          });

          let currentIndex = 0;

          function animateModel() {
            requestAnimationFrame(animateModel);

            if (movingModel.position.distanceTo(points[currentIndex]) < 0.1) {
              currentIndex = (currentIndex + 1) % points.length;
            }

            const targetPosition = points[currentIndex];
            const direction = targetPosition.clone().sub(movingModel.position).normalize();
            const distance = movingModel.position.distanceTo(targetPosition);
            const speed = 30; // 调整速度以适应移动效果
            const step = speed * 0.1; // 调整步长以适应不同的移动速度

            // 让模型朝向目标位置
            movingModel.lookAt(targetPosition);
            // 逆时针旋转90度
            movingModel.rotateY(Math.PI / 2);

            if (distance > step) {
              movingModel.position.addScaledVector(direction, step);
            } else {
              movingModel.position.copy(targetPosition);
            }
          }

          console.log('Three.js版本：', THREE.REVISION);
          animateModel();
        });
      });
    },
    animate() {
      if (!this.renderer) {
        return;
      }
      requestAnimationFrame(this.animate);
      this.controls.update();
      TWEEN.update();
      this.renderer.render(this.scene, this.camera);
      if (!this.tagRenderer) {
        return;
      }
      this.tagRenderer.render(this.scene, this.camera);
    },
    onWindowResize() {
      this.camera.aspect = window.innerWidth / window.innerHeight;
      this.camera.updateProjectionMatrix();
      this.renderer.setSize(window.innerWidth, window.innerHeight);
      this.tagRenderer.setSize(window.innerWidth, window.innerHeight);
      const size = window.innerWidth / 100;
      this.loadModel(size, size, size);
    },
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.onWindowResize);
  }
};
</script>

<style>
.label {
  white-space: nowrap;
  font-size: 14px; /* 增加标签标记的字体大小 */
  font-weight: bold; /* 设置标签标记文本加粗 */
  color: white; /* 将标签标记文本颜色设置为白色 */
  background-color: rgba(51, 51, 51, 0.2); /* 设置标签标记的背景颜色 */
  border-radius: 5px; /* 添加圆角边框到标签标记 */
  padding: 0px 0px; /* 在标签标记文本周围添加内边距 */
  position: relative;
  y-index: 9; /* 确保标签标记显示在其他元素之上 */
  border-width: 1px 1px; /* 设置宽度为10像素，长度为5像素的边框 */
  border-style: solid;
  border-color: #eeeeee; /* 设置边框颜色为红色 */

  &:hover {
    color: aqua;
  }
}
</style>
