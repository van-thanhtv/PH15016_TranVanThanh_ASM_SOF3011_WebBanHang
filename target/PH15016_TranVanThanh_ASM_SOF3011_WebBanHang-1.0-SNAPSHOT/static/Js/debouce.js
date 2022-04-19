/**
 * 
 */
function debouce(func, wait) {
	let timeout;
	return (...args) => {
		console.log('before timeout->' + timeout)
		clearTimeout(timeout);
		timeout = setTimeout(() => {
			func.apply(this, args);
		}, wait);
	}
}