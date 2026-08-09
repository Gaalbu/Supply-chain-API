import http from 'k6/http';
import { check } from 'k6';

export const options = {
  vus: Number(__ENV.VUS || 5),
  duration: __ENV.DURATION || '30s',
  thresholds: {
    http_req_failed: ['rate<0.01'],
    http_req_duration: ['p(95)<1000'],
  },
};

const baseUrl = __ENV.BASE_URL || 'http://localhost:8080';
const trackingCode = __ENV.TRACKING_CODE || 'PKG001';

export default function () {
  const response = http.get(`${baseUrl}/api/pacotes/${encodeURIComponent(trackingCode)}`);
  check(response, {
    'tracking endpoint returns 200': (result) => result.status === 200,
  });
}
