QRCode.toCanvas(document.getElementById('canvasQr'), urlQrCode, function (error) {
  if (error) console.error(error)
  console.log('success!');
})
