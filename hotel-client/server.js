const express = require('express');
const path = require('path');
const app = express();
const PORT = 3000;

app.use(express.static(__dirname));
app.use(express.json());

app.get('/api/capacity-check', async (req, res) => {
    const { guests, capacity } = req.query;

    // 1. Validation Check: query parameters දෙකම තියෙනවද බලන්න
    if (!guests || !capacity) {
        return res.status(400).json({ error: 'Missing required query parameters: guests and capacity' });
    }

    try {
        // 2. Safe URL construction using URLSearchParams
        const gatewayUrl = new URL('http://hotel-gateway-container:8080/api/rooms/capacity-check');
        gatewayUrl.searchParams.append('guests', guests);
        gatewayUrl.searchParams.append('capacity', capacity);

        const response = await fetch(gatewayUrl, {
            method: 'GET'
        });

        if (!response.ok) {
            return res.status(response.status).json({ 
                error: 'Gateway security exception or auth failed',
                status: response.status 
            });
        }

        // 3. Preserve original Content-Type from gateway response
        const contentType = response.headers.get('content-type');
        if (contentType) {
            res.setHeader('Content-Type', contentType);
        }

        const textData = await response.text();
        res.status(200).send(textData);

    } catch (error) {
        // Microservice down වෙන වෙලාවට හෝ Connection Refused වෙනකොට handling
        console.error('Fetch error:', error.message);
        res.status(500).json({ error: 'Gateway Connection Failed: ' + error.message });
    }
});

app.listen(PORT, () => {
    console.log(`Hotel web client running at: http://localhost:${PORT}`);
});