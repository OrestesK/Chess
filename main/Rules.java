package main;

public class Rules {
	public static boolean pawn(Piece piece, int XClicked, int YClicked, Handler handler) {
		if (!inBetween(piece, XClicked, YClicked, handler)) {
			if (piece.getMoves() <= 0) {
				if (piece.getXTile() == XClicked) {
					if ((piece.getYTile() + 2 == YClicked) || piece.getYTile() + 1 == YClicked) {
						for (int i = 0; i < handler.object.size(); i++) {
							Piece tempObject = handler.object.get(i);
							if (tempObject.getXTile() == XClicked && tempObject.getYTile() == YClicked
									&& tempObject.getTeam() != piece.getTeam()) {
								return false;
							}
						}
						return true;
					}
				}
				if (piece.getYTile() + 1 == YClicked) {
					if ((piece.getXTile() + 1 == XClicked) || (piece.getXTile() - 1 == XClicked)) {
						for (int i = 0; i < handler.object.size(); i++) {
							Piece tempObject = handler.object.get(i);
							if (tempObject.getXTile() == XClicked && tempObject.getYTile() == YClicked
									&& tempObject.getTeam() != piece.getTeam()) {
								return true;
							}
						}
					}
				}
			} else {
				if (piece.getXTile() == XClicked) {
					if (piece.getYTile() + 1 == YClicked) {
						for (int i = 0; i < handler.object.size(); i++) {
							Piece tempObject = handler.object.get(i);
							if (tempObject.getXTile() == XClicked && tempObject.getYTile() == YClicked
									&& tempObject.getTeam() != piece.getTeam()) {
								return false;
							}
						}
						return true;
					}
				}
				if (piece.getYTile() + 1 == YClicked) {
					if ((piece.getXTile() + 1 == XClicked) || (piece.getXTile() - 1 == XClicked)) {
						for (int i = 0; i < handler.object.size(); i++) {
							Piece tempObject = handler.object.get(i);
							if (tempObject.getXTile() == XClicked && tempObject.getYTile() == YClicked
									&& tempObject.getTeam() != piece.getTeam()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean king(Piece piece, int XClicked, int YClicked, Handler handler) {
		if (!inBetween(piece, XClicked, YClicked, handler)) {
			if (piece.getXTile() == XClicked) {
				if ((piece.getYTile() + 1 == YClicked) || (piece.getYTile() - 1 == YClicked)) {
					return true;
				}
			}
			if (piece.getYTile() == YClicked) {
				if ((piece.getXTile() + 1 == XClicked) || (piece.getXTile() - 1 == XClicked)) {
					return true;
				}
			}
			if (piece.getYTile() + 1 == YClicked) {
				if ((piece.getXTile() + 1 == XClicked) || (piece.getXTile() - 1 == XClicked)) {
					return true;
				}
			}
			if (piece.getYTile() - 1 == YClicked) {
				if ((piece.getXTile() + 1 == XClicked) || (piece.getXTile() - 1 == XClicked)) {
					return true;
				}
			}
		}
		if(castleBoolean(piece, XClicked, YClicked, handler)) {
			return true;
		}
		return false;

	}

	public static boolean knight(Piece piece, int XClicked, int YClicked, Handler handler) {
		if (piece.getYTile() + 2 == YClicked) {
			if ((piece.getXTile() + 1 == XClicked) || (piece.getXTile() - 1 == XClicked)) {
				return true;
			}
		}
		if (piece.getYTile() - 2 == YClicked) {
			if ((piece.getXTile() + 1 == XClicked) || (piece.getXTile() - 1 == XClicked)) {
				return true;
			}
		}
		if (piece.getXTile() + 2 == XClicked) {
			if ((piece.getYTile() + 1 == YClicked) || (piece.getYTile() - 1 == YClicked)) {
				return true;
			}
		}
		if (piece.getXTile() - 2 == XClicked) {
			if ((piece.getYTile() + 1 == YClicked) || (piece.getYTile() - 1 == YClicked)) {
				return true;
			}
		}
		return false;

	}

	public static boolean rook(Piece piece, int XClicked, int YClicked, Handler handler) {
		if (!inBetween(piece, XClicked, YClicked, handler)) {
			if (piece.getXTile() == XClicked) {
				return true;
			}
			if (piece.getYTile() == YClicked) {
				return true;
			}
		}
		return false;

	}

	public static boolean queen(Piece piece, int XClicked, int YClicked, Handler handler) {
		if (!inBetween(piece, XClicked, YClicked, handler)) {
			if (piece.getXTile() == XClicked) {
				return true;
			}
			if (piece.getYTile() == YClicked) {
				return true;
			}
		}
		if (!inBetweenDiagonal(piece, XClicked, YClicked, handler)) {
			for (int i = 1; i <= 8; i++) {
				if (piece.getYTile() + i == YClicked) {
					if ((piece.getXTile() + i == XClicked) || (piece.getXTile() - i == XClicked)) {
						return true;
					}
				}
				if (piece.getYTile() - i == YClicked) {
					if ((piece.getXTile() + i == XClicked) || (piece.getXTile() - i == XClicked)) {
						return true;
					}
				}
			}
		}
		return false;

	}

	public static boolean bishop(Piece piece, int XClicked, int YClicked, Handler handler) {
		if (!inBetweenDiagonal(piece, XClicked, YClicked, handler)) {
			for (int i = 1; i <= 8; i++) {
				if (piece.getYTile() + i == YClicked) {
					if ((piece.getXTile() + i == XClicked) || (piece.getXTile() - i == XClicked)) {
						return true;
					}
				}
				if (piece.getYTile() - i == YClicked) {
					if ((piece.getXTile() + i == XClicked) || (piece.getXTile() - i == XClicked)) {
						return true;
					}
				}
			}
		}
		return false;

	}

	public static boolean inBetween(Piece piece, int XClicked, int YClicked, Handler handler) {
		for (int i = 0; i < handler.object.size(); i++) {
			Piece tempObject = handler.object.get(i);
			if (tempObject.getXTile() == piece.getXTile()) {
				if (((tempObject.getYTile() < YClicked) && (tempObject.getYTile() > piece.getYTile()))
						|| ((tempObject.getYTile() > YClicked) && (tempObject.getYTile() < piece.getYTile()))) {
					return true;
				}
			}
			if (tempObject.getYTile() == piece.getYTile()) {
				if (((tempObject.getXTile() < XClicked) && (tempObject.getXTile() > piece.getXTile()))
						|| ((tempObject.getXTile() > XClicked) && (tempObject.getXTile() < piece.getXTile()))) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean inBetweenDiagonal(Piece piece, int XClicked, int YClicked, Handler handler) {
		for (int z = 0; z < handler.object.size(); z++) {
			Piece tempObject = handler.object.get(z);
			for (int i = 1; i <= 8; i++) {
				if (tempObject.getXTile() == piece.getXTile() + i && tempObject.getYTile() == piece.getYTile() + i) {
					if ((tempObject.getXTile() < XClicked) && (tempObject.getXTile() > piece.getXTile())) {
						if (((tempObject.getYTile() < YClicked) && (tempObject.getYTile() > piece.getYTile()))) {
							return true;
						}
					}
				}
				if (tempObject.getXTile() == piece.getXTile() - i && tempObject.getYTile() == piece.getYTile() + i) {
					if ((tempObject.getXTile() > XClicked) && (tempObject.getXTile() < piece.getXTile())) {
						if (((tempObject.getYTile() < YClicked) && (tempObject.getYTile() > piece.getYTile()))) {
							return true;
						}
					}
				}
				if (tempObject.getXTile() == piece.getXTile() - i && tempObject.getYTile() == piece.getYTile() - i) {
					if ((tempObject.getXTile() > XClicked) && (tempObject.getXTile() < piece.getXTile())) {
						if (((tempObject.getYTile() > YClicked) && (tempObject.getYTile() < piece.getYTile()))) {
							return true;
						}
					}
				}
				if (tempObject.getXTile() == piece.getXTile() + i && tempObject.getYTile() == piece.getYTile() - i) {
					if ((tempObject.getXTile() < XClicked) && (tempObject.getXTile() > piece.getXTile())) {
						if (((tempObject.getYTile() > YClicked) && (tempObject.getYTile() < piece.getYTile()))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean castle(Piece piece, int XClicked, int YClicked, Handler handler) {
		if (!inBetween(piece, XClicked, YClicked, handler) && piece.getId() == ID.King) {
			if (piece.getTeam() == 1) {
				if (piece.getMoves() == 0) {
					if (piece.getXTile() - 3 == XClicked) {
						for (int i = 0; i < handler.object.size(); i++) {
							Piece tempObject = handler.object.get(i);
							if (tempObject.getMoves() == 0 && tempObject.getTeam() == piece.getTeam()
									&& tempObject.getVariant() == 1 && tempObject.getId() == ID.Rook) {
								piece.setXTile(2);
								tempObject.setXTile(3);
								return true;
							}
						}
					}
					if (piece.getXTile() + 2 == XClicked) {
						for (int i = 0; i < handler.object.size(); i++) {
							Piece tempObject = handler.object.get(i);
							if (tempObject.getMoves() == 0 && tempObject.getTeam() == piece.getTeam()
									&& tempObject.getVariant() == 2 && tempObject.getId() == ID.Rook) {
								piece.setXTile(7);
								tempObject.setXTile(6);
								return true;
							}
						}
					}
				}
			} else {
				if (piece.getMoves() == 0) {
					if (piece.getXTile() - 2 == XClicked) {
						for (int i = 0; i < handler.object.size(); i++) {
							Piece tempObject = handler.object.get(i);
							if (tempObject.getMoves() == 0 && tempObject.getTeam() == piece.getTeam()
									&& tempObject.getVariant() == 2 && tempObject.getId() == ID.Rook) {
								piece.setXTile(2);
								tempObject.setXTile(3);
								return true;
							}
						}
					}
					if (piece.getXTile() + 3 == XClicked) {
						for (int i = 0; i < handler.object.size(); i++) {
							Piece tempObject = handler.object.get(i);
							if (tempObject.getMoves() == 0 && tempObject.getTeam() == piece.getTeam()
									&& tempObject.getVariant() == 1 && tempObject.getId() == ID.Rook) {
								piece.setXTile(7);
								tempObject.setXTile(6);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public static boolean castleBoolean(Piece piece, int XClicked, int YClicked, Handler handler) {
		if (!inBetween(piece, XClicked, YClicked, handler) && piece.getId() == ID.King) {
			if (piece.getTeam() == 1) {
				if (piece.getMoves() == 0) {
					if (piece.getXTile() - 3 == XClicked) {
						for (int i = 0; i < handler.object.size(); i++) {
							Piece tempObject = handler.object.get(i);
							if (tempObject.getMoves() == 0 && tempObject.getTeam() == piece.getTeam()
									&& tempObject.getVariant() == 1 && tempObject.getId() == ID.Rook) {
								return true;
							}
						}
					}
					if (piece.getXTile() + 2 == XClicked) {
						for (int i = 0; i < handler.object.size(); i++) {
							Piece tempObject = handler.object.get(i);
							if (tempObject.getMoves() == 0 && tempObject.getTeam() == piece.getTeam()
									&& tempObject.getVariant() == 2 && tempObject.getId() == ID.Rook) {
								return true;
							}
						}
					}
				}
			} else {
				if (piece.getMoves() == 0) {
					if (piece.getXTile() - 2 == XClicked) {
						for (int i = 0; i < handler.object.size(); i++) {
							Piece tempObject = handler.object.get(i);
							if (tempObject.getMoves() == 0 && tempObject.getTeam() == piece.getTeam()
									&& tempObject.getVariant() == 2 && tempObject.getId() == ID.Rook) {
								return true;
							}
						}
					}
					if (piece.getXTile() + 3 == XClicked) {
						for (int i = 0; i < handler.object.size(); i++) {
							Piece tempObject = handler.object.get(i);
							if (tempObject.getMoves() == 0 && tempObject.getTeam() == piece.getTeam()
									&& tempObject.getVariant() == 1 && tempObject.getId() == ID.Rook) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}
