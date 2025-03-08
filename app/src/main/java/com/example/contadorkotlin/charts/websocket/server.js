const { Server } = require('ws');

const wss = new Server({ port: 8080 });

wss.on('connection', ws => {
  console.log('Cliente conectado');

  let count = 0;
  const maxMessages = 3;

  const interval = setInterval(() => {
    if (count >= maxMessages) {
      clearInterval(interval);
      console.log('Envío de datos completado');
      return;
    }

    const data = JSON.stringify({
      value: Math.floor(Math.random() * 100),
      label: `Dato ${count + 1}`,
      color: `#${Math.floor(Math.random() * 16777215).toString(16)}`
    });

    ws.send(data);
    count++;
  }, 2000);

  ws.on('close', () => {
    console.log('Cliente desconectado');
    clearInterval(interval);
  });
});

console.log('WebSocket server escuchando en el puerto 8080');
