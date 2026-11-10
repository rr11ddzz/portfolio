const buttons = document.querySelectorAll('[data-filter]');
const cards = document.querySelectorAll('[data-category]');
buttons.forEach(button => button.addEventListener('click', () => {
  const filter = button.dataset.filter;
  buttons.forEach(item => item.setAttribute('aria-pressed', String(item === button)));
  let count = 0;
  cards.forEach(card => {
    card.hidden = filter !== 'all' && card.dataset.category !== filter;
    if (!card.hidden) count++;
  });
  document.getElementById('project-count').textContent = `Showing ${count} ${count === 1 ? 'project' : 'projects'}`;
}));
