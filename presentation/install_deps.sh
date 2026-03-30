#!/bin/bash

install_on_mac() {
  brew install pandoc mactex quarto mermaid-cli
  quarto install --no-prompt extension pandoc-ext/diagram
}

install_on_fedora() {
  sudo dnf install -y pandoc texlive python3-pip pnpm
  pip install quarto-cli
  pnpm install -g @mermaid-js/mermaid-cli
  quarto install --no-prompt extension pandoc-ext/diagram
}

command -v brew && install_on_mac
command -v dnf  && install_on_fedora

