<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal-input modal-container">
	<div class="child-container">
		<div class="title-modal">Nhập email người bạn muốn share:</div>
		<div>
			<textarea name="" id="input-modal" cols="5" rows="10"></textarea>
			<p
				style="font-size: 12.5px; color: yellowgreen; display: none; transition: 0.4s ease-in-out;">You
				must enter character!</p>
		</div>
		<div class="modal-container-action">
			<div class="modal-cancel">Cancel</div>
			<div class="input-modal-continue">Continue</div>
		</div>
	</div>
</div>

<script>
<%@include file='/static/Js/modal.js' %>
window.addEventListener('DOMContentLoaded', () => {
	function showModalInput(payload) { // payload is object
        document.querySelector('.modal-input.modal-container').style.display = 'block';
        document.querySelector('.modal-input .child-container').classList.add('show');
        let modalAction = document.querySelectorAll('.modal-input > div > .modal-container-action')[0];
        let errorNotice = document.querySelector('.modal-input.modal-container > div > div:nth-child(2) > p');
        errorNotice.style.display = 'none';
        
        modalAction.removeChild(document.querySelector('.modal-input .input-modal-continue'));

        document.querySelector('.modal-input .title-modal').innerText=payload.title;
        let inputContainer = document.createElement('div');
        inputContainer.className = 'input-modal-continue';
        inputContainer.innerText = 'Continue';
		modalAction.appendChild(inputContainer);
		
        inputContainer.addEventListener('click', function () {
        	
            let input = document.getElementById('input-modal');
            let errorNotice = document.querySelector('.modal-input.modal-container > div > div:nth-child(2) > p');
            console.log(input.value.length);
            if(input == null)
            {
            	console.log('here11')
            	errorNotice.style.display = '';
            	errorNotice.innerText = 'You must enter character!';
            }
            else if(input.value.length ==0){
            	errorNotice.style.display = '';
            	errorNotice.innerText = 'You must enter character!';
            }
            else
	        	if(payload.validate(input.value)){
	        	 payload['input'] = input.value;
	             payload['dispatch'](payload);
	             document.querySelector('.modal-input .child-container').classList.remove('show');
	             document.querySelector('.modal-input').style.display = 'none';
	             errorNotice.style.display = 'none';
	        	}
	        	else
	        		errorNotice.innerText = payload.message;
        });
    }
	//end immediate
	
	function sendMailShare(action) {
		fetch('http://localhost:8080/SOF3011_ASSIGNMENT/ajaxAdmin?action=shareVideo&videoId='+action.video+'&email='+action.input)
		.then(res=>res.json()).then(result=>{
		    if(result.result ==='success')
		    	alert("Send mail success!");
		    else
		    	alert(result.message);
		}).catch(error=>{
			console.log(error)
		    alert('You need login to use this feature!')
		})	
		
	}; //end sendmail
	
	document.querySelectorAll('.video-container > .video-item > .video-action > .video-share-action')
	.forEach(element=>{
		let videoId = element.parentNode.dataset.id;
		element.addEventListener('click', ()=>{
			showModalInput({
				title: 'Nhập email người bạn muốn share: ',
                video: videoId,
                dispatch:sendMailShare,
                validate: (input)=>{
                	return input.match(/^\w\w+@\w+.+\w+$/g)==null? false: true;
                },
                message: 'Email invalid, please try again!'
            })
		})
	})
});
	
</script>

<style>
	<%@include file='/static/css/modal.css' %>
</style>