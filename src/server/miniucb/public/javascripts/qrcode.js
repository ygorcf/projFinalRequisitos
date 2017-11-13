QRCode.toCanvas(document.getElementById('canvasQr'), 'sample text', function (error) {
  if (error) console.error(error)
  console.log('success!');
})