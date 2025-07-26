<template>
  <div ref="container" class="box"></div>
</template>

<script>
import * as THREE from 'three';
import {GLTFLoader} from 'three/examples/jsm/loaders/GLTFLoader.js';
import {OrbitControls} from 'three/examples/jsm/controls/OrbitControls.js';

var scene= new THREE.Scene();
var camera=null;
var renderer = null;
var ambientLight = null;
var controls = null;
export default {
  mounted() {
    this.initScene();
    this.loadModel();
    window.addEventListener('resize', this.onWindowResize);
  },
  methods: {
    initScene() {
      const container = this.$refs.container;
      let width = container.clientWidth;
      let height = container.clientHeight;

      this.scene = new THREE.Scene();
      this.scene.background = new THREE.Color( 0x2c3e6c ); // 背景颜色

      this.camera = new THREE.PerspectiveCamera(75, width / height, 0.1, 1000);
      this.camera.position.set(0, 300, 50);
      this.camera.lookAt(0, 0, 0);

      this.renderer = new THREE.WebGLRenderer({ antialias: true });
      this.renderer.setSize(width, height);
      container.appendChild(this.renderer.domElement);

      const axesHelper = new THREE.AxesHelper(5);
      this.scene.add(axesHelper);

      this.controls = new OrbitControls(this.camera, this.renderer.domElement);
      //灯光设置
      this.ambientLight = new THREE.AmbientLight(0xffffff, 0.5);
      this.scene.add(this.ambientLight);

      const directionalLight = new THREE.DirectionalLight(0xffffff, 1);
      directionalLight.position.set(0, 40);
      this.scene.add(directionalLight);

      this.animate();
    },
    adjustSize(width, height) {
      // 根据传入的宽度和高度调整摄像机和渲染器的大小
      this.camera.aspect = width / height;
      this.camera.updateProjectionMatrix();
      this.renderer.setSize(width, height);
    },
    loadModel() {
      const gltfLoader = new GLTFLoader();
      const scene = this.scene; // 将this.scene存储在局部变量以供内部函数访问

      gltfLoader.load("/module/map1.glb", (gltf1) => {
        const baseModel = gltf1.scene;
        baseModel.position.set(-20, 0, 0);
        scene.add(baseModel);
        // 逆时针旋转90°
        gltf1.scene.rotation.y = Math.PI / 2; // 绕Y轴旋转

        gltfLoader.load("/module/car2.glb", (gltf2) => {
          const movingModel = gltf2.scene;
          movingModel.position.set(-380, 0, -60);
          // movingModel.position.set(180, 0, 75);
          scene.add(movingModel);
          // 逆时针旋转90°
          gltf2.scene.rotation.y = Math.PI / 2; // 绕Y轴旋转
          const points = [
            new THREE.Vector3(-160, 0, -60),
            new THREE.Vector3(-160, 0, 75),
            new THREE.Vector3(35, 0, 75),
            new THREE.Vector3(35, 0, -60),
            new THREE.Vector3(160, 0, -60),
            new THREE.Vector3(160, 0, 75),
            new THREE.Vector3(35, 0, 75),
            new THREE.Vector3(35, 0, -25),
            new THREE.Vector3(-160, 0, -25),
          ];

          let currentIndex = 0;

          function animateModel() {
            requestAnimationFrame(animateModel);

            if (movingModel.position.distanceTo(points[currentIndex]) < 0.1) {
              currentIndex = (currentIndex + 1) % points.length;
            }

            const targetPosition = points[currentIndex];
            const direction = targetPosition.clone().sub(movingModel.position).normalize();
            const distance = movingModel.position.distanceTo(targetPosition);
            const speed =20; // 调整速度以适应移动效果
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
          console.log('Three.js版本：',THREE.REVISION);
          animateModel();
        });
      });
    },
    onWindowResize() {
      const container = this.$refs.container;
      let width = container.clientWidth;
      let height = container.clientHeight;

      if (this.camera) {
        this.camera.aspect = width / height;
        this.camera.updateProjectionMatrix();
      }
      if (this.renderer) {
        this.renderer.setSize(width, height);
      }
      this.render(); // 重新渲染场景
    },
    animate() {
      requestAnimationFrame(this.animate);
      if (this.controls) this.controls.update();
      this.renderer.render(this.scene, this.camera);
    },
    render() {
      this.renderer.render(this.scene, this.camera);
    },
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.onWindowResize);
    if (this.renderer) {
      this.renderer.dispose();
    }
    if (this.scene) {
      // 清理场景中的所有对象
      while (this.scene.children.length > 0) {
        this.scene.remove(this.scene.children[0]);
      }
    }
  },
};
</script>

<style>
.box {
  width: 100%; /* 占满父容器宽度 */
  height: 90%; /* 占满父容器高度 */
  overflow: hidden;
  position: relative;
}
</style>
