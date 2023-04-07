const ctx = document.getElementById('myChart');

new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['03-19', '03-20', '03-21', '03-22', '03-23', '03-24', '03-25'],
        datasets: [
            {
                label: '방문자 수',
                data: [5, 24, 12, 32, 15, 21, 34],
                borderColor: '#27ae60',
                fill: true,
                backgroundColor: 'rgb(236, 243, 236, 0.6)'
            },
            {
                label: '페이지 뷰',
                data: [12, 52, 24, 82, 32, 65, 92],
                borderColor: '#577391',
                fill: true,
                backgroundColor: 'rgb(152, 195, 255, 0.3)'
            }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true,
            }
        }
    }
});