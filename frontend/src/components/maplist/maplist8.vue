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
      this.scene.background = new THREE.Color(0x2c3e6c); // 背景颜色

      this.camera = new THREE.PerspectiveCamera(75, width / height, 0.1, 1000);
      this.camera.position.set(0, 50, 0); // 初始相机位置在Z轴正方向

      this.renderer = new THREE.WebGLRenderer({antialias: true});
      this.renderer.setSize(width, height);
      container.appendChild(this.renderer.domElement);


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
      const loader = new GLTFLoader();
      loader.load("/module/xinmian.glb", (gltf) => {
        this.model = gltf.scene;
        this.model.position.set(-30, -10, 0); // 模型位置设置为原点
        this.model.scale.set(2, 2, 2); // 放大模型，这里的2表示在每个轴方向上放大2倍
        this.scene.add(this.model);
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
  padding-top:15%;
  width: 85%; /* 占满父容器宽度 */
  height:70%;
  overflow: hidden;
  position: relative;
}
</style>
