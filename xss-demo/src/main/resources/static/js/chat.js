// Simulating some client-side data that could be valuable to an attacker
const userSession = {
	id: "testUser",
	token: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9",
	lastLogin: "2023-07-13T10:30:00Z"
};
const privateMessages = [
	{ from: "You", content: "Hey guys, I was trying to contact John on SocialSphere but he wasn't responding. Can someone share his number?" },
	{ from: "Alice", content: "Sure, here is John's number - 8089845234." },
	{ from: "Bob", content: "Hi guys, can someone please share their Netflix account with me?" },
	{ from: "Sara", content: "Sure, here are the credentials- Netflix ID: user@example.com, Password: Pass123" },
	{ from: "Bob", content: "Got it. Thanks for sharing." }
	];

// Chat popup functionality
const openChatBtn = document.getElementById('openChat');
const chatPopup = document.getElementById('chatPopup');
const closeChatBtn = document.getElementById('closeChat');
const chatMessages = document.getElementById('chatMessages');
const messageInput = document.getElementById('messageInput');
const sendMessageBtn = document.getElementById('sendMessage');

chatPopup.style.display = 'none';

openChatBtn.addEventListener('click', () => {
	chatPopup.style.display = 'block';
	displayMessages();
});

closeChatBtn.addEventListener('click', () => {
	chatPopup.style.display = 'none';
});

function displayMessages() {
	chatMessages.innerHTML = '';
	privateMessages.forEach(msg => {
		const messageDiv = document.createElement('div');
		messageDiv.style.marginBottom = '10px';
		messageDiv.innerHTML = `<strong>${msg.from}:</strong> ${msg.content}`;
		chatMessages.appendChild(messageDiv);
	});
	chatMessages.scrollTop = chatMessages.scrollHeight;
}

sendMessageBtn.addEventListener('click', () => {
	const content = messageInput.value.trim();
	if (content) {
		privateMessages.push({ from: 'You', content: content });
		displayMessages();
		messageInput.value = '';
	}
});

messageInput.addEventListener('keypress', (e) => {
	if (e.key === 'Enter') {
		sendMessageBtn.click();
	}
});

// Initialize chat messages when the script is loaded
displayMessages();