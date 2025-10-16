const fromInput = document.getElementById("fromInput");
const toInput = document.getElementById("toInput");
const swapBtn = document.getElementById("swapBtn");
const dateField = document.getElementById("dateField");
const dateValue = document.getElementById("dateValue");
const chipToday = document.getElementById("chipToday");
const chipTomorrow = document.getElementById("chipTomorrow");
const dp = document.getElementById("datePicker");
const dpGrid = document.getElementById("dpGrid");
const dpMonthLabel = document.getElementById("dpMonthLabel");
const dpCurrentLabel = document.getElementById("dpCurrentLabel");
const dpPrev = document.getElementById("dpPrev");
const dpNext = document.getElementById("dpNext");

let selectedDate = new Date();
let viewYear = selectedDate.getFullYear();
let viewMonth = selectedDate.getMonth();

function fmt(d) {
  return d.toLocaleDateString(undefined, {
    day: "2-digit",
    month: "short",
    year: "numeric",
  });
}

function updateDateLabel() {
  dateValue.textContent = fmt(selectedDate);
  if (dpCurrentLabel) dpCurrentLabel.textContent = fmt(selectedDate);
}
updateDateLabel();

const today = new Date();
const minYear = today.getFullYear();
const minMonth = today.getMonth();

function updateNavDisabled() {
  dpPrev.disabled = viewYear === minYear && viewMonth === minMonth;
}

function buildCalendar(year, month) {
  const first = new Date(year, month, 1);
  const startDay = (first.getDay() + 6) % 7; // Monday-first grid
  const daysInMonth = new Date(year, month + 1, 0).getDate();
  const monthName = new Date(year, month, 1).toLocaleDateString(undefined, {
    month: "long",
    year: "numeric",
  });
  dpMonthLabel.textContent = monthName;
  updateNavDisabled();
  dpGrid.innerHTML = "";
  for (let i = 0; i < startDay; i++) {
    const cell = document.createElement("div");
    cell.className = "dp-cell disabled";
    dpGrid.appendChild(cell);
  }
  for (let day = 1; day <= daysInMonth; day++) {
    const d = new Date(year, month, day);
    const cell = document.createElement("div");
    cell.className = "dp-cell";
    cell.textContent = String(day);
    const minDate = new Date(minYear, minMonth, today.getDate());
    const isPast = d < minDate;
    const isWeekend = d.getDay() === 6 || d.getDay() === 0;
    if (isWeekend) cell.classList.add("weekend");
    if (d.toDateString() === selectedDate.toDateString())
      cell.classList.add("selected");
    if (isPast) {
      cell.classList.add("disabled");
    } else {
      cell.addEventListener("click", () => {
        selectedDate = d;
        updateDateLabel();
        chipToday.classList.toggle("active", isToday(selectedDate));
        const tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        chipTomorrow.classList.toggle(
          "active",
          selectedDate.toDateString() === tomorrow.toDateString()
        );
        dp.hidden = true;
        dateField.setAttribute("aria-expanded", "false");
      });
    }
    dpGrid.appendChild(cell);
  }
}

function isToday(d) {
  const t = new Date();
  return (
    d.getFullYear() === t.getFullYear() &&
    d.getMonth() === t.getMonth() &&
    d.getDate() === t.getDate()
  );
}

function openPicker() {
  viewYear = selectedDate.getFullYear();
  viewMonth = selectedDate.getMonth();
  buildCalendar(viewYear, viewMonth);
  dp.hidden = false;
  dateField.setAttribute("aria-expanded", "true");
}
function closePicker(e) {
  if (!dp.hidden && !dp.contains(e.target) && !dateField.contains(e.target)) {
    dp.hidden = true;
    dateField.setAttribute("aria-expanded", "false");
  }
}
// Only open the picker if it is currently hidden
dateField.addEventListener("click", (e) => {
  if (dp.hidden) openPicker();
});
dateField.addEventListener("keydown", (e) => {
  if ((e.key === "Enter" || e.key === " ") && dp.hidden) openPicker();
});
document.addEventListener("click", closePicker);

dpPrev.addEventListener("click", (e) => {
  e.stopPropagation();
  if (--viewMonth < 0) {
    viewMonth = 11;
    viewYear--;
  }
  buildCalendar(viewYear, viewMonth);
});
dpNext.addEventListener("click", (e) => {
  e.stopPropagation();
  if (++viewMonth > 11) {
    viewMonth = 0;
    viewYear++;
  }
  buildCalendar(viewYear, viewMonth);
});

chipToday.addEventListener("click", () => {
  selectedDate = new Date();
  chipToday.classList.add("active");
  chipTomorrow.classList.remove("active");
  updateDateLabel();
});
chipTomorrow.addEventListener("click", () => {
  selectedDate = new Date();
  selectedDate.setDate(selectedDate.getDate() + 1);
  chipTomorrow.classList.add("active");
  chipToday.classList.remove("active");
  updateDateLabel();
});

swapBtn.addEventListener("click", () => {
  const tmp = fromInput.value;
  fromInput.value = toInput.value;
  toInput.value = tmp;
});

document.getElementById("searchBtn").addEventListener("click", () => {
  alert(
    `Searching trains from ${fromInput.value} to ${toInput.value} on ${fmt(
      selectedDate
    )}`
  );
});
