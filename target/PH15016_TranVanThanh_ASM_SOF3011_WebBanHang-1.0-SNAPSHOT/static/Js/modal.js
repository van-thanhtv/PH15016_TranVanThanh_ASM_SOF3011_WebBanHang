/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	document.getElementsByClassName('child-container')[0].addEventListener('click', (e) => {
		e.stopPropagation();
	});

	document.querySelectorAll('.modal-container').forEach(element => {
		element.addEventListener('click', () => {
			closeModalInput();
		})
	});

	document.querySelectorAll('.modal-cancel').forEach(element => {
		element.addEventListener('click', function() {
			closeModalInput();
		});
	})

	function closeModalInput() {
            console.log('here')
            document.querySelectorAll('.child-container').forEach(element=>{
                element.classList.remove('show')
            });
            document.querySelectorAll('.modal-container').forEach(element=>{
                element.style.display = 'none';
            })
        }; //end close modal input
});