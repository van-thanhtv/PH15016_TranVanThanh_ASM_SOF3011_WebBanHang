<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal-confirm modal-container">
	<div class="child-container">
		<div class="title-modal">Bạn chắc chắn?</div>
		<div class="modal-container-action">
			<div class="modal-cancel">Cancel</div>
			<div class="modal-confirm-action">Confirm</div>
		</div>
	</div>
</div>

<style>
	<%@include file='/static/css/modal.css' %>
</style>

<script>
		<%@include file='/static/Js/modal.js' %>
        function showModalConfirm(payload) {
            document.getElementsByClassName('modal-confirm')[0].style.display = 'block';
            document.getElementsByClassName('child-container')[0].classList.add('show');
            let modalAction = document.querySelectorAll('.modal-confirm > div > .modal-container-action')[0];
            modalAction.removeChild(document.querySelectorAll('.modal-confirm .modal-confirm-action')[0]);

            let confirmBtn = document.createElement('div');
            confirmBtn.innerText='Confirm';
            confirmBtn.className = 'modal-confirm-action';
            modalAction.appendChild(confirmBtn);
            confirmBtn.addEventListener('click', (e) => {
                payload['dispatch']();
            })
        }
        // end modal confirm

</script>