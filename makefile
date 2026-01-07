.DEFAULT_GOAL := build

install-deps:
	@./install_deps.sh

build: slides.pdf

slides.pdf: presentation/slides.md
	@echo "Building slides ..."
	@cd presentation && pandoc -t beamer -f markdown+implicit_figures slides.md -o slides.pdf
