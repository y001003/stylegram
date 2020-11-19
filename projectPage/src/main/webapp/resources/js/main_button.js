const msnry = new Masonry(document.getElementById("container"), {
    itemSelector: ".item",
    columnWidth: ".item",
    gutter: ".gutterSizer",
    percentPosition: !0
});

infiniteScroll({
    container: "#container",
    item: ".item",
    next: ".next",
    prev: ".prev",
    nextButton: ".nextBtn",
    prevButton: ".prevBtn",
    nextLoader: ".nextLoader",
    prevLoader: ".prevLoader",
    pushHistory: true
}),

window.addEventListener("load", () => {
    msnry.layout()
})