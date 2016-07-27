"use strict";

var bebop = require('node-bebop');

var drone = bebop.createClient();

drone.connect(function() {
  drone.takeOff();

  // setTimeout(function() {
  //   drone.right(10);
  // }, 2000);

  // setTimeout(function() {
  //   drone.stop();
  // }, 3000);

  // setTimeout(function() {
  // 	drone.forward(10);
  // }, 2000);

  // setTimeout(function() {
  //   drone.stop();
  // }, 6000);

  // setTimeout(function() {
  //   drone.left(10);
  // }, 6000);

  // setTimeout(function() {
  //   drone.stop();
  // }, 7000);

  // setTimeout(function(){
  // 	drone.backward(10);
  // }, 8000);

  // setTimeout(function() {
  //   drone.stop();
  // }, 9000);

  setTimeout(function(){
  	drone.clockwise(30);
  },2000);

  setTimeout(function() {
    drone.land();
  }, 6000);
});
